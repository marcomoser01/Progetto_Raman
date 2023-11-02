const fs = require('fs');
const { exec } = require('child_process');
const { promisify } = require('util');
const execAsync = promisify(exec);

const mainDirectory = process.cwd();
const MAX_CONCURRENT_PROCESSES = 3;

const subDirectories = fs.readdirSync(mainDirectory, { withFileTypes: true })
  .filter(dirent => dirent.isDirectory())
  .map(dirent => `${mainDirectory}/${dirent.name}`);

const mavenDirectories = subDirectories.filter(dir => fs.existsSync(`${dir}/pom.xml`))


let runningProcessesCount = 0
let processCount = 0
runConcurrentProcesses()

// recursive function
function runConcurrentProcesses() {
  runningProcessesCount++
  runMavenCleanPackage(mavenDirectories[processCount]).then(() => {
    runningProcessesCount--
  }).catch(err => { console.error('ERROR', err); })
  processCount++

  //exit condition
  if (processCount < mavenDirectories.length) {
    if (runningProcessesCount < MAX_CONCURRENT_PROCESSES) {
      runConcurrentProcesses()
    } else {
      waitUntil()
        .then(() => {
          runConcurrentProcesses()
        }).catch(err => console.error('error', err))
    }
  }
}

async function waitUntil() {
  return await new Promise((resolve, reject) => {
    const interval = setInterval(() => {
      if (runningProcessesCount < MAX_CONCURRENT_PROCESSES) {
        resolve('1 proccess is now available'); //these aren't printed to console
        reject('Didn\'t work somehow')
        clearInterval(interval);
      }
    }, 500);
  });
}

async function runMavenCleanPackage(directory) {
  console.log(`Launching mvn clean package in ${dirToLog(directory)}...`)
  try {
    const { stdout, stderr } = await execAsync('mvn clean package', { cwd: directory })
    if (stdout.includes('BUILD SUCCESS')) {
      console.log(`\nCOMMAND EXECUTED SUCCESSFULLY IN FOLDER -> ${dirToLog(directory)}`);
    } else if (stdout.includes('error')) {
      console.error('il comando agg\'sbagliat (。_。)');
    }
    if (stderr) {
      console.error(stderr)
    }
  } catch (error) {
    console.error(`Error in ${directory}. ${error}`)
  }
}

function dirToLog(folderPath) {
  return folderPath.substring(folderPath.lastIndexOf('/'))
}
