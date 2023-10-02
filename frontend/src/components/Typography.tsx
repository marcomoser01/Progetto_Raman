import { FunctionComponent, ReactNode } from 'react'

interface Props {
	children: ReactNode
	variant?: string
}

const Typography: FunctionComponent<Props> = ({ children, variant }) => {
	switch (variant) {
		case 'h1':
			return (
				<h1 className="scroll-m-20 text-4xl font-bold tracking-tight lg:text-5xl">
					{children}
				</h1>
			)
		case 'h2':
			return (
				<h2 className="scroll-m-20 pb-2 text-3xl font-semibold tracking-tight transition-colors first:mt-0">
					{children}
				</h2>
			)
		case 'h3':
			return (
				<h3 className="scroll-m-20 text-2xl font-semibold tracking-tight">
					{children}
				</h3>
			)
		case 'h4':
			return (
				<h4 className="scroll-m-20 text-xl font-semibold tracking-tight">
					{children}
				</h4>
			)
		case 'p':
			return <p className="leading-7 [&:not(:first-child)]:mt-6">{children}</p>
		case 'code':
			return (
				<code className="relative rounded bg-muted px-[0.3rem] py-[0.2rem] font-mono text-sm font-semibold">
					{children}
				</code>
			)
		case 'quote':
			return (
				<blockquote className="mt-6 border-l-2 pl-6 italic">
					{children}
				</blockquote>
			)
		default:
			return <p className="leading-7 [&:not(:first-child)]:mt-6">{children}</p>
	}
}

export default Typography
