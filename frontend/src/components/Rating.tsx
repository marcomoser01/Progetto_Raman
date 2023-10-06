/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/ban-ts-comment */

import {
	Card,
	CardContent,
	CardDescription,
	CardHeader,
	CardTitle,
} from '@/components/ui/card'
import { StarFilledIcon, StarIcon } from '@radix-ui/react-icons'
import { useEffect, useState } from 'react'

import { RatingType } from '@/lib/types'
import { fetchUser } from '@/lib/fetch'

export default function Rating(rating: RatingType) {
	const [username, setUsername] = useState<string>()
	//@ts-ignore
	rating = rating.rating //don't ask

	function throwStarsInTheUniverse() {
		const stars = [0, 0, 0, 0, 0]

		for (let filledStars = 0; filledStars < rating.vote; filledStars++) {
			stars[filledStars] = 1
		}

		// console.log(`Stars: ${rating.vote}/5 - ${stars}`)
		return stars
	}

	useEffect(() => {
		fetchUser(rating.userId).then(user => {
			if (user) {
				setUsername(user.name)
			} else {
				setUsername('<No username detected>')
			}
		})
		//fetch all ratings I guess. so that i can put them in the table.
	}, [])

	return (
		<Card className="mb-4">
			<CardHeader className="pb-2">
				{/* <div className="flex justify-start gap-4 items-end"> */}
				<CardTitle>{username}</CardTitle>
				<CardDescription className="font-mono leading-none">
					<div className="flex">
						{throwStarsInTheUniverse().map(starBool =>
							starBool ? (
								<StarFilledIcon className="[&>path]:fill-slate-950" />
							) : (
								<StarIcon />
							)
						)}
					</div>
				</CardDescription>
				{/* </div> */}
			</CardHeader>
			<CardContent>
				<pre className="font-sans">{rating.message}</pre>
			</CardContent>
		</Card>
	)
}