import { Button } from '@/components/ui/button'
import MyNav from '@/components/MyNav'

function App() {
	return (
		<>
			<header className="p-10 flex justify-center bg-slate-600">
				<MyNav />
			</header>
			<main>
				<Button>testing stuff</Button>
			</main>
			<footer></footer>
		</>
	)
}

export default App
