/* eslint-disable @typescript-eslint/no-unused-vars */

import * as z from 'zod'

import {
	Form,
	FormControl,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from '@/components/ui/form'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Link } from 'react-router-dom'
import { Textarea } from '@/components/ui/textarea'
import Typography from '@/components/Typography'
import { cn } from '@/lib/utils'
import { fetchAddProduct } from '@/lib/fetch'
import { toast } from '@/components/ui/use-toast'
import { useForm } from 'react-hook-form'
import { useState } from 'react'
import { zodResolver } from '@hookform/resolvers/zod'

const FormSchema = z.object({
	title: z.string().min(2, {
		message: 'Title must be at least 2 characters',
	}),
	category: z.string().min(2, {
		message: 'Category must be at least 2 characters',
	}),
	quantity: z.coerce.number().int().min(0, {
		message: 'Quantity must be a positive integer number',
	}),
	price: z.coerce
		.number()
		.min(0, {
			message: 'Quantity must be a positive number',
		})
		.refine(
			n => {
				return (
					/^([\d]+)$/.test(n.toString()) ||
					n.toString().split('.')[1]?.length <= 2
				)
			},
			() => ({ message: 'Max precision is 2 decimal places' })
		),
	description: z.string().min(5, {
		message: 'Description must be at least 5 characters',
	}),
})

export default function AddPRoduct() {
	const [submitted, setSubmitted] = useState<boolean>(false)

	const form = useForm<z.infer<typeof FormSchema>>({
		resolver: zodResolver(FormSchema),
	})

	async function onSubmit(data: z.infer<typeof FormSchema>) {
		const result = await fetchAddProduct(data)
		if (result && Object.keys(result).length !== 0) {
			toast({
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
		if (!result || Object.keys(result).length === 0) {
			toast({
				title: 'An error occurred!',
				description: "⊙﹏⊙∥ Either a fetch error or logic one. Don't know",
			})
		}
	}

	function revealLinkToProductList() {}

	return (
		<main className="w-fit mx-auto px-2 py-4">
			<Typography variant="h1">Add Your Product</Typography>
			<br />

			<Form {...form}>
				<form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6 w-80">
					<FormField
						control={form.control}
						name="title"
						render={({ field }) => (
							<FormItem>
								<FormLabel>Product Title</FormLabel>
								<FormControl>
									<Input maxLength={100} {...field} />
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name="description"
						render={({ field }) => (
							<FormItem>
								<FormLabel>Product Description</FormLabel>
								<FormControl>
									<Textarea
										rows={5}
										maxLength={1000}
										placeholder="Your description of the product"
										{...field}
									/>
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name="category"
						render={({ field }) => (
							<FormItem>
								<FormLabel>Category</FormLabel>
								<FormControl>
									<Input
										maxLength={30}
										placeholder="Category name"
										{...field}
									/>
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name="quantity"
						render={({ field }) => (
							<FormItem>
								<FormLabel>Quantity</FormLabel>
								<FormControl>
									<Input
										type="number"
										min={0}
										max={10000}
										placeholder="How many?"
										{...field}
									/>
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<FormField
						control={form.control}
						name="price"
						render={({ field }) => (
							<FormItem>
								<FormLabel>Price</FormLabel>
								<FormControl>
									<Input maxLength={15} placeholder="How much?" {...field} />
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
							<Link to="/">Back to Product List ⏪</Link>
						</Button>
					</div>
				</form>
			</Form>
		</main>
	)
}
