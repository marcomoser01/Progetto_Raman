/* eslint-disable @typescript-eslint/no-unused-vars */

import * as z from 'zod'

import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from '@/components/ui/form'
import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Link } from 'react-router-dom'
import { Product } from '@/lib/types'
import { Textarea } from '@/components/ui/textarea'
import Typography from '@/components/Typography'
import { cn } from '@/lib/utils'
import { fetchAddRatingToProduct } from '@/lib/fetch'
import { toast } from '@/components/ui/use-toast'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'

const FormSchema = z.object({
	userId: z.coerce.number().int().min(1, {
		message: 'userId must be at least 2 characters.',
	}),
	vote: z.coerce.number().int().min(0).max(5, {
		message: 'Vote must be an integer number between 0 and 5.',
	}),
	message: z.string().min(10, {
		message: 'Message must be at least 10 characters.',
	}),
})

export default function AddRating() {
	const [product, setProduct] = useState<Product>()
	const [submitted, setSubmitted] = useState<boolean>(false)

	useEffect(() => {
		setProduct(() => JSON.parse(localStorage.getItem('product') || ''))
	}, [])

	const form = useForm<z.infer<typeof FormSchema>>({
		resolver: zodResolver(FormSchema),
	})

	async function onSubmit(data: z.infer<typeof FormSchema>) {
		let result = undefined
		if (product) {
			result = await fetchAddRatingToProduct(
				product.id,
				data.userId,
				data.vote,
				data.message
			)
			if (result && Object.keys(result).length !== 0) {
				toast({
					className: cn(
						'top-0 right-0 flex fixed md:max-w-[420px] md:top-4 md:right-4'
					),
					title: 'You submitted the following values:',
					description: (
						<pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-2">
							<code className="text-white">
								{JSON.stringify(result, null, 2)}
							</code>
						</pre>
					),
				})
				setSubmitted(true)
			}
			console.log(data)
			console.log(result)
		}
		if (!product || !result || Object.keys(result).length === 0) {
			toast({
				title: 'An error occurred!',
				description: <p>⊙﹏⊙∥ Either a fetch error or logic one. Don't know</p>,
			})
		}
	}

	if (product && Object.keys(product).length !== 0) {
		return (
			<main className="w-fit mx-auto px-2 py-4">
				<Typography variant="h1">Add Your Rating</Typography>
				<br />

				<Form {...form}>
					<form
						onSubmit={form.handleSubmit(onSubmit)}
						className="space-y-6 w-80"
					>
						<FormField
							control={form.control}
							name="userId"
							render={({ field }) => (
								<FormItem>
									<FormLabel>User ID</FormLabel>
									<FormControl>
										<Input type="number" placeholder="Your userId" {...field} />
									</FormControl>
									<FormDescription>
										Write your user identification number
									</FormDescription>
									<FormMessage />
								</FormItem>
							)}
						/>
						<FormField
							control={form.control}
							name="vote"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Vote</FormLabel>
									<FormControl>
										<Input type="number" placeholder="Your vote" {...field} />
									</FormControl>
									<FormDescription>
										Place a vote between 0 and 5
									</FormDescription>
									<FormMessage />
								</FormItem>
							)}
						/>
						<FormField
							control={form.control}
							name="message"
							render={({ field }) => (
								<FormItem>
									<FormLabel>Message</FormLabel>
									<FormControl>
										<Textarea
											rows={5}
											placeholder="Your comment on the product"
											{...field}
										/>
									</FormControl>
									<FormMessage />
								</FormItem>
							)}
						/>
						<div className="flex justify-between">
							<Button type="submit">Submit 🚀</Button>
							<Button
								type="button"
								asChild
								className={cn({ hidden: !submitted })}
							>
								<Link to="/Product">Back to Product ⏪</Link>
							</Button>
						</div>
					</form>
				</Form>
			</main>
		)
	} else {
		return (
			<main className="w-fit mx-auto px-2 py-4">
				<Typography variant="h1">No product detected (#`-_ゝ-)</Typography>
				<Button asChild className="mt-4">
					<Link to="/list">Back Home 🏠</Link>
				</Button>
			</main>
		)
	}
}
