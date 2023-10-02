import { Separator } from '@/components/ui/separator'
import { Button } from '@/components/ui/button'
import Typography from '@/components/Typography'

export default function Product() {
	return (
		<main className="max-w-5xl mx-auto my-4">
			{/* insert product name */}
			<Typography variant="h1">
				Product Name: <span className="bg-lime-400 px-2 rounded">XXXXX</span>
			</Typography>
			<Typography variant="p">
				Category: <span className="bg-lime-400 px-2 rounded">XXXXX</span>
			</Typography>
			<Typography variant="p">
				Quantity: <span className="bg-lime-400 px-2 rounded">XXXXX</span>
			</Typography>

			<Separator className="my-4" />
			<Typography variant="h2">Ratings</Typography>
			<Button className="mb-3">Add rating</Button>
			<Typography variant="p">
				Vote: <span className="bg-lime-400 px-2 rounded">da 1 a 5</span>
			</Typography>
			<Typography variant="p">
				User <span className="bg-lime-400 px-2 rounded">XX XXX</span> wrote:{' '}
				<span className="bg-lime-400 px-2 rounded">
					Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate a
					sit quaerat, omnis aut enim, veritatis explicabo vero quis labore
					voluptatum fuga in quasi delectus. Provident rerum exercitationem
					quaerat, est eveniet repudiandae ex molestias commodi? Atque molestias
					necessitatibus beatae totam.
				</span>
			</Typography>
		</main>
	)
}
