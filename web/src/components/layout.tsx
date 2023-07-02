import type { ReactNode } from "react";
import { AppShell, Navbar, Header } from "@mantine/core";

export default function Layout({ children }: { children: ReactNode }) {
  return (
    <AppShell
      padding="md"
      navbar={
        <Navbar width={{ base: 300 }} height={500} p="xs">
          {/* Navbar content */}
        </Navbar>
      }
      header={
        <Header height={60} p="xs">
          {/* Header content */}
        </Header>
      }
      styles={(theme) => ({
        main: {
          backgroundColor:
            theme.colorScheme === "dark"
              ? theme.colors.dark[8]
              : theme.colors.gray[0],
        },
      })}
    >
      <main className="flex min-h-screen flex-col items-center justify-between p-24">
        {children}
      </main>
    </AppShell>
  );
}
