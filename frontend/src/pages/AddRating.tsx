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

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Link } from 'react-router-dom'
import { Textarea } from '@/components/ui/textarea'
import Typography from '@/components/Typography'
import { toast } from '@/components/ui/use-toast'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'

interface Props {
	productId?: string | number
}

const FormSchema = z.object({
	userId: z.coerce.number().int().min(2, {
		message: 'userId must be at least 2 characters.',
	}),
	vote: z.coerce.number().int().min(0).max(5, {
		message: 'Vote must be an integer number between 0 and 5.',
	}),
	message: z.string().min(10, {
		message: 'Message must be at least 10 characters.',
	}),
})

export default function AddRating({ productId = 1234 }: Props) {
	const form = useForm<z.infer<typeof FormSchema>>({
		resolver: zodResolver(FormSchema),
	})

	function onSubmit(data: z.infer<typeof FormSchema>) {
		toast({
			title: 'You submitted the following values:',
			description: (
				<pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
					<code className="text-white">{JSON.stringify(data, null, 2)}</code>
				</pre>
			),
		})
		console.log(data)
	}

	if (productId) {
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
						<Button type="submit">Submit 🚀</Button>
					</form>
				</Form>
			</main>
		)
	} else {
		return (
			<main className="w-fit mx-auto px-2 py-4">
				<Typography variant="h1">No product id detected (#`-_ゝ-)</Typography>
				<Button asChild className="mt-4">
					<Link to="/list">back home 🏠</Link>
				</Button>
			</main>
		)
	}
}
