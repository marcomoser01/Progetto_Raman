/* eslint-disable @typescript-eslint/no-unused-vars */

import Typography from "@/components/Typography"
import { Button } from "@/components/ui/button"
import { fetchUsers } from "@/lib/fetch"
import { User } from "@/lib/types"
import { cn } from "@/lib/utils"
import { AvatarIcon, HomeIcon, PlusIcon } from "@radix-ui/react-icons"
import { useState, useEffect } from "react"
import { Link } from "react-router-dom"

export default function ProductList() {
  const [users, setUsers] = useState<User[]>()

  useEffect(() => {
    fetchUsers().then(data => {
      console.log(data)
      setUsers(data)
    })
  }, [])

  return (
    <main className="max-w-2xl mx-auto my-4 px-2 py-4">
      <div className='flex flex-row justify-between items-center'>
        <Typography variant="h1">Users List</Typography>

        <div className='flex justify-center gap-2 flex-col'>
          <Link to="/AddUser">
            <Button className='w-full justify-start'>
              <PlusIcon className="mr-2 h-4 w-4" />
              Add User
            </Button>
          </Link>

          <Link to="/">
            <Button variant="outline" className='w-full justify-start'>
              <HomeIcon className="mr-2 h-4 w-4" />
              Back Home
            </Button>
          </Link>
        </div>
      </div>
      {/* table */}
      <div
        className={cn('my-6 w-full overflow-y-auto', {
          hidden: !users || users?.length === 0,
        })}
      >
        <table className="w-full">
          {/* HEADER */}
          <thead>
            <tr className="m-0 border-t p-0 even:bg-muted">
              <th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
                ID
              </th>
              <th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
                Name
              </th>
              <th className="border px-4 py-2 text-left font-bold [&[align=center]]:text-center [&[align=right]]:text-right">
                Surname
              </th>
            </tr>
          </thead>

          {/* DATA */}
          <tbody>
            {users &&
              users
                .sort((a, b) => b.id - a.id) //descending order
                .map((user, index) => {
                  return (
                    <tr
                      className="m-0 border-t p-0 even:bg-gray-100"
                      key={index}
                    >
                      <td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
                        {user.id || -1}
                      </td>
                      <td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
                        {user.name || 'no data'}
                      </td>
                      <td className="border px-4 py-2 text-left [&[align=center]]:text-center [&[align=right]]:text-right">
                        {user.cognome || 'no data'}
                      </td>
                    </tr>
                  )
                })}
          </tbody>
        </table>
      </div>

      {(!users || users?.length === 0) && (
        <Typography variant="p">
          (┬┬﹏┬┬) There seems to be no users left.
          <br />
          Add one clicking the button above.
        </Typography>

      )}
    </main>
  )
}
