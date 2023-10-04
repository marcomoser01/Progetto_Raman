/* eslint-disable @typescript-eslint/ban-ts-comment */

import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from '@/components/ui/card'

import { RatingType } from '@/lib/types'
import Typography from '@/components/Typography'

export default function Rating(rating: RatingType) {
	//@ts-ignore
	rating = rating.rating //don't ask

	return (
		<Card className="mb-4">
			<CardHeader>
				<CardTitle>{'<Nome Utente>'}</CardTitle>
				<CardDescription>{rating.vote}/5</CardDescription>
			</CardHeader>
			<CardContent>
				<p>{rating.message}</p>
			</CardContent>
		</Card>
	)
}
