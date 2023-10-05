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
			<CardHeader className="pb-2">
				<div className="flex justify-start gap-4 items-baseline">
					<CardDescription className="font-mono">
						{rating.vote}/5
					</CardDescription>
					<CardTitle>{'<Nome Utente>'}</CardTitle>
				</div>
			</CardHeader>
			<CardContent>
				<pre className="font-sans">{rating.message}</pre>
			</CardContent>
		</Card>
	)
}
