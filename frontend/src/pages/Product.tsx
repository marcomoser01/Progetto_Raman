/* eslint-disable @typescript-eslint/ban-ts-comment */
/* eslint-disable @typescript-eslint/no-unused-vars */

import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Link } from 'react-router-dom'
import Rating from '@/components/Rating'
import { Separator } from '@/components/ui/separator'
import Typography from '@/components/Typography'
import { fetchRatings } from '@/lib/fetch'
import { Product, RatingType } from '@/lib/types'

export default function Product() {
	const [ratings, setRatings] = useState<RatingType[]>()
	const [product, setProduct] = useState<Product>()

	useEffect(() => {
		setProduct(() => {
			return JSON.parse(localStorage.getItem('product') || '')
		})
	}, [])

	// retrieve ratings of specific product
	useEffect(() => {
		if (product && typeof product === 'object') {
			fetchRatings(product.id).then(data => {
				setRatings(data)
			})
		}
	}, [product])

	if (product) {
		return (
			<main className="max-w-3xl mx-auto my-4 px-2 py-4">
				<Typography variant="h1">{product.title}</Typography>
				<Typography variant="p">
					Description:{' '}
					<span className="text-gray-500">{product.description}</span>
				</Typography>
				<Typography variant="p">
					Category: <span className="text-gray-500">{product.category}</span>
				</Typography>
				<Typography variant="p">
					Quantity: <span className="text-gray-500">{product.quantity}</span>
				</Typography>
				<Typography variant="p">
					Price: <span className="text-gray-500">{product.price}€</span>
				</Typography>
				<Separator className="my-4" />
				<div className="flex justify-between items-baseline">
					<Typography variant="h2">Ratings</Typography>
					<Button className="mb-8" asChild>
						<Link to="/addRating">Add Rating</Link>
					</Button>
				</div>

				{ratings &&
					ratings.map((rating, index) => (
						<div key={index}>
							{/*@ts-ignore*/}
							<Rating rating={rating.rating} />
						</div>
					))}

				{(!ratings || ratings.length === 0) && (
					<Typography variant="p">
						≡(▔﹏▔)≡ There are no ratings yet. Create one by clicking the button
						above
					</Typography>
				)}
			</main>
		)
	} else {
		return (
			<Typography variant="h4">
				¯\_(ツ)_/¯ We're having some problems fetching data...
			</Typography>
		)
	}
}
