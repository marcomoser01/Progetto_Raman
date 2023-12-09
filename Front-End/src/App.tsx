import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'
import Typography from '@/components/Typography'

function App() {
	return (
		<main className="w-fit mx-auto px-2 py-4">
			<Typography variant="h1">Catalog Project</Typography>
			<Button asChild>
				<Link to="/list">Go to Product List</Link>
			</Button>
		</main>
	)
}

export default App
