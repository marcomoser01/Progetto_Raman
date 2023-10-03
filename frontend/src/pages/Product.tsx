/* eslint-disable @typescript-eslint/no-unused-vars */

import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'
import Rating from '@/components/Rating'
import { Separator } from '@/components/ui/separator'
import Typography from '@/components/Typography'

export default function Product() {
	const [ratings, setRatings] = useState<object[] | []>()

	async function fetchRatings() {
		const response = await fetch(URL_GET_RATINGS, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
			},
			// body: JSON.stringify(data)
		})
		if (!response.ok) {
			return []
		}

		const ratingsJSON = await response.json()
		if (!ratingsJSON.ok) {
			return []
		}

		const retrievedRatings: object[] = JSON.parse(ratingsJSON)
		if (!Array.isArray(retrievedRatings)) {
			return []
		}
		console.log('retrievedRatings:\n', retrievedRatings)

		return retrievedRatings || []
	}

	//retrieve ratings of specific product
	useEffect(() => {
		fetchRatings().then(data => {
			setRatings(data)
		})
	}, [])

	return (
		<main className="max-w-3xl mx-auto my-4">
			{/* insert product name */}
			<Typography variant="h1">
				Product Name: <span className="bg-gray-300 px-2 rounded">XXXXX</span>
			</Typography>
			<Typography variant="p">
				Category: <span className="bg-gray-300 px-2 rounded">XXXXX</span>
			</Typography>
			<Typography variant="p">
				Quantity: <span className="bg-gray-300 px-2 rounded">XXXXX</span>
			</Typography>

			<Separator className="my-4" />

			<Typography variant="h2">Ratings</Typography>

			<Button className="mb-8" asChild>
				<Link to="/addRating">Add Rating</Link>
			</Button>

			{ratings &&
				ratings.map(rating => (
					<Rating
						userId={rating.userId}
						productId={rating.productId}
						vote={rating.vote || 0}
						description={rating.description}
					/>
				))}

			{!ratings && (
				<Typography variant="p">
					≡(▔﹏▔)≡ There is no rating yet. Create one by clicking the button
					above
				</Typography>
			)}
		</main>
	)
}
