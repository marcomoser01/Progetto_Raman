import Typography from './Typography'

interface Props {
	userId: number
	productId: number
	vote: number
	description: string
}

export default function Rating({
	userId = 0,
	productId = 0,
	vote = 0,
	description = '',
}: Props) {
	return (
		<div className="mb-8">
			<Typography variant="p">
				Vote: <span className="bg-gray-300 px-2 rounded">{vote}</span>
			</Typography>
			<Typography variant="p">
				Product Id:{' '}
				<span className="bg-gray-300 px-2 rounded">{productId}</span>
			</Typography>
			<Typography variant="p">
				User <span className="bg-gray-300 px-2 rounded">{userId}</span> wrote:{' '}
				<span className="bg-gray-300 px-2 rounded">{description}</span>
			</Typography>
		</div>
	)
}
