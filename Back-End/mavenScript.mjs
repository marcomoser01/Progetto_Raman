import { readdirSync, existsSync } from 'fs'
import { exec } from 'child_process'
import { promisify } from 'util'
const execAsync = promisify(exec)

const mainDirectory = process.cwd()
const MAX_CONCURRENT_PROCESSES = 3

const subDirectories = readdirSync(mainDirectory, { withFileTypes: true })
	.filter(dirent => dirent.isDirectory())
	.map(dirent => `${mainDirectory}/${dirent.name}`)

const mavenDirectories = subDirectories.filter(dir =>
	existsSync(`${dir}/pom.xml`)
)

let runningProcessesCount = 0
let processCount = 0
let processesCompletedSuccessfully = 0
const processesExecuted = []
let nErrors = 0

if (mavenDirectories.length === 0)
	console.log('No first level folders containing maven projects')
else runConcurrentProcesses()

// end
function happilyEverAfter() {
	return processesCompletedSuccessfully === mavenDirectories.length
		? console.log(
				'\n----------------------------------------------------\nEvery command is executed successfully (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ \n----------------------------------------------------\n'
		  )
		: console.error(
				`\nNot all commands did their job right. <(_ _)> 
		\nSuccessful processes:\t\t\t ${processesCompletedSuccessfully} 
Directories with maven projects:\t ${mavenDirectories.length}
		\nThese values should be equal\n`
		  )
}

function areAllDirRan() {
	//remove duplicates
	const arr1 = [...new Set(mavenDirectories)]
	const arr2 = [...new Set(processesExecuted)]

	const resultArray = arr1.filter(itemOfArr1 =>
		arr2.find(itemOfArr2 => itemOfArr1 === itemOfArr2)
	)

	return resultArray.length === arr1.length
}

// **** FUNCTIONS ****

// recursive function
function runConcurrentProcesses(dir = mavenDirectories[processCount]) {
	//? replace processcount with processesCompletedSuccessfully in the above line
	runningProcessesCount++

	runMavenCleanPackage(dir)
		.then(() => {
			nErrors = 0
			processesCompletedSuccessfully++
		})
		.catch(err => {
			nErrors++
			console.error('ERROR', err)
			if (nErrors > 3) {
				console.error('\nToo many retries, bye!\n')
				process.abort()
			}
			runConcurrentProcesses() //TODO be sure to run failed command in directory
		})
		.finally(() => {
			runningProcessesCount--
			if (areAllDirRan()) return happilyEverAfter()
		})

	//right after `async runMavenCleanPackage()` started
	processCount++

	//exit condition
	if (processCount < mavenDirectories.length) {
		if (runningProcessesCount < MAX_CONCURRENT_PROCESSES) {
			runConcurrentProcesses()
		} else {
			waitUntil()
				.then(() => {
					runConcurrentProcesses()
				})
				.catch(err => console.error('error', err))
		}
	}
}

async function waitUntil() {
	return await new Promise((resolve, reject) => {
		const interval = setInterval(() => {
			if (runningProcessesCount < MAX_CONCURRENT_PROCESSES) {
				resolve('1 proccess is now available') //these aren't printed to console
				reject("Didn't work somehow")
				clearInterval(interval)
			}
		}, 250)
	})
}

async function runMavenCleanPackage(directory) {
	console.log(`Launching mvn clean package in ${logDir(directory)}...`)

	try {
		const { stdout, stderr } = await execAsync('mvn clean package', {
			cwd: directory,
		})

		if (stdout.includes('BUILD SUCCESS')) {
			console.log(
				`\nCOMMAND EXECUTED SUCCESSFULLY IN FOLDER -> ${logDir(directory)}`
			)
		} else if (stdout.includes('ERROR')) {
			console.error("il comando agg'sbagliat (。_。)")
		}

		if (stderr) {
			console.error('[ERROR]', stderr)
		}
	} catch (error) {
		console.error(
			`[ERROR] in ${logDir(directory || 'no folder provided')}\n${error}`
		)
		throw new Error()
	} finally {
		processesExecuted.push(directory)
	}
}

function logDir(path) {
	return path.substring(path.lastIndexOf('/'))
}
