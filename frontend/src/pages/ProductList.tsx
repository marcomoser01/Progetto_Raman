import Typography from '@/components/Typography'
import { Button } from '@/components/ui/button'

export default function ProductList() {
	return (
		<main className="max-w-5xl mx-auto my-4">
			<Typography variant="h1">Product List</Typography>
			<br />
			<Button>Add Product</Button>
			{/* table */}
			<div className="my-6 w-full overflow-y-auto">
				<table className="w-full">
					{/* HEADER */}
					<thead>
						<tr className="m-0 border-t p-0 even:bg-muted">
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Product
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Category
							</th>
							<th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
								Quantity
							</th>
						</tr>
					</thead>

					{/* DATA */}
					<tbody>
						<tr className="m-0 border-t p-0 even:bg-muted" tabIndex={0}>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Prodotto 1
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Categoria A
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								2
							</td>
						</tr>

						<tr className="m-0 border-t p-0 even:bg-muted" tabIndex={0}>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Prodotto 2
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Categoria F
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								55
							</td>
						</tr>

						<tr className="m-0 border-t p-0 even:bg-muted" tabIndex={0}>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Prodotto 3
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								Categoria A
							</td>
							<td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
								33
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</main>
	)
}
