import Layout from '@/components/layout'
import { Button } from '@mantine/core'
import Link from 'next/link'

export default function Home() {
  return (
    <Layout>
      hi
      <Button>
        <Link href='/page1'>Page One</Link>
      </Button>
      <Button>
        <Link href='/page2'>Page Two</Link>
      </Button>
    </Layout>
  )
}
