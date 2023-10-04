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
	const [ratings, setRatings] = useState<RatingType[] | [] | undefined>()
	const [product, setProduct] = useState<Product>()

	useEffect(() => {
		setProduct(() => {
			return JSON.parse(localStorage.getItem('product') || '')
		})
	}, [])

	//retrieve ratings of specific product
	// useEffect(() => {
	// 	fetchRatings(product.id).then(data => {
	// 		setRatings(data)
	// 	})
	// }, [])

	if (product) {
		return (
			<main className="max-w-3xl mx-auto my-4 px-2 py-4">
				{/* insert product name */}
				<Typography variant="h1">
					Product Name:{' '}
					<span className="bg-gray-300 px-2 rounded">{product.title}</span>{' '}
					{product.id}
				</Typography>
				<Typography variant="p">
					Description:{' '}
					<span className="bg-gray-300 px-2 rounded">
						{product.description}
					</span>
				</Typography>
				<Typography variant="p">
					Category:{' '}
					<span className="bg-gray-300 px-2 rounded">{product.category}</span>
				</Typography>
				<Typography variant="p">
					Quantity:{' '}
					<span className="bg-gray-300 px-2 rounded">{product.quantity}</span>
				</Typography>
				<Typography variant="p">
					Price:{' '}
					<span className="bg-gray-300 px-2 rounded">{product.price}€</span>
				</Typography>

				<Separator className="my-4" />

				<Typography variant="h2">Ratings</Typography>

				<Button className="mb-8" asChild>
					<Link to="/addRating">Add Rating</Link>
				</Button>

				{/* {ratings &&
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
			)} */}
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
