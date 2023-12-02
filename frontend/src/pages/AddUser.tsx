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
import { useEffect, useState } from 'react'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Link } from 'react-router-dom'
import { User } from '@/lib/types'
import Typography from '@/components/Typography'
import { cn } from '@/lib/utils'
import { fetchAddUser } from '@/lib/fetch'
import { toast } from '@/components/ui/use-toast'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { ArrowLeftIcon, HomeIcon, RocketIcon } from '@radix-ui/react-icons'

const FormSchema = z.object({
  name: z.string().min(3, {
    message: 'Name must be at least 3 characters.',
  }),
  cognome: z.string().min(3, {
    message: 'Cognome must be at least 3 characters.',
  }),
})

export default function AddUser() {
  const [user, setUser] = useState<User>()
  const [submitted, setSubmitted] = useState<boolean>(false)


  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
  })

  async function onSubmit(data: z.infer<typeof FormSchema>) {
    let result = undefined
    if (user) {
      result = await fetchAddUser({ name: user.name, cognome: user.cognome })

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
    if (!user || !result || Object.keys(result).length === 0) {
      toast({
        title: 'An error occurred!',
        description: <p>⊙﹏⊙∥ Either a fetch error or logic one. Don't know</p>,
      })
    }
  }

  if (user && Object.keys(user).length !== 0) {
    return (
      <main className="w-fit mx-auto px-2 py-4">
        <Typography variant="h1">Add a New User</Typography>
        <br />

        <Form {...form}>
          <form
            onSubmit={form.handleSubmit(onSubmit)}
            className="space-y-6 w-80"
          >
            <FormField
              control={form.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Username</FormLabel>
                  <FormControl>
                    <Input type="number" placeholder="Your username" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="cognome"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Surname</FormLabel>
                  <FormControl>
                    <Input type="number" placeholder="Your surname" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <div className="flex justify-between">
              <Button type="submit"><RocketIcon className="mr-2 h-4 w-4" />Submit</Button>
              <Link to="/Users">
                <Button
                  type="button"
                  className={cn({ hidden: !submitted })}
                >
                  <ArrowLeftIcon className="mr-2 h-4 w-4" />
                  Back to Users
                </Button>
              </Link>
            </div>
          </form>
        </Form>
      </main>
    )
  } else {
    return (
      <main className="w-fit mx-auto px-2 py-4">
        <Typography variant="h1">No user detected (#`-_ゝ-)</Typography>
        <Link to="/list">
          <Button className="mt-4">
            <HomeIcon className="mr-2 h-4 w-4" />
            Back Home
          </Button>
        </Link>
      </main >
    )
  }
}
