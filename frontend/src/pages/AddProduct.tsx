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
import { Textarea } from '@/components/ui/textarea'
import Typography from '@/components/Typography'
import { fetchAddProduct } from '@/lib/fetch'
import { toast } from '@/components/ui/use-toast'
import { useForm } from 'react-hook-form'
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
	price: z.coerce.number().int().min(0, {
		message: 'Quantity must be a positive integer number',
	}),
	description: z.string().min(5, {
		message: 'Description must be at least 5 characters',
	}),
})

export default function AddPRoduct() {
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
			setTimeout(() => {
				location.assign('/')
			}, 2000)
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
									<Input {...field} />
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
									<Input placeholder="Category name" {...field} />
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
									<Input
										type="number"
										min={0}
										placeholder="How much?"
										{...field}
									/>
								</FormControl>
								<FormMessage />
							</FormItem>
						)}
					/>
					<Button type="submit">Submit 🚀</Button>
				</form>
			</Form>
		</main>
	)
}
