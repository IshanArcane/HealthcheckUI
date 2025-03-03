import { motion } from "framer-motion"
import { useForm } from "react-hook-form"
import { User, Mail, Lock, Phone } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"

export default function RegisterForm() {
  const form = useForm({
    defaultValues: {
      name: "",
      email: "",
      phone: "",
      password: "",
      confirmPassword: "",
    },
  })

  const onSubmit = (data) => {
    console.log(data)
  }

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
      className="w-full max-w-md p-8 bg-gray-900/50 backdrop-blur-lg rounded-lg shadow-xl"
    >
      <h2 className="text-3xl font-bold text-white mb-6">Register</h2>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
          <FormField
            control={form.control}
            name="name"
            rules={{ required: "Full name is required" }}
            render={({ field }) => (
              <FormItem>
                <FormLabel required className="text-white">
                  Full Name
                </FormLabel>
                <div className="relative">
                  <User className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" />
                  <FormControl>
                    <Input
                      {...field}
                      className="pl-10 bg-gray-800 border-gray-700 text-white"
                      placeholder="Enter your full name"
                    />
                  </FormControl>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="email"
            rules={{
              required: "Email is required",
              pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                message: "Invalid email address",
              },
            }}
            render={({ field }) => (
              <FormItem>
                <FormLabel required className="text-white">
                  Email
                </FormLabel>
                <div className="relative">
                  <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" />
                  <FormControl>
                    <Input
                      {...field}
                      className="pl-10 bg-gray-800 border-gray-700 text-white"
                      placeholder="Enter your email"
                    />
                  </FormControl>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="phone"
            rules={{
              required: "Phone number is required",
              pattern: {
                value: /^[0-9]{10}$/,
                message: "Invalid phone number",
              },
            }}
            render={({ field }) => (
              <FormItem>
                <FormLabel required className="text-white">
                  Phone Number
                </FormLabel>
                <div className="relative">
                  <Phone className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" />
                  <FormControl>
                    <Input
                      {...field}
                      className="pl-10 bg-gray-800 border-gray-700 text-white"
                      placeholder="Enter your phone number"
                    />
                  </FormControl>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="password"
            rules={{
              required: "Password is required",
              minLength: {
                value: 8,
                message: "Password must be at least 8 characters",
              },
            }}
            render={({ field }) => (
              <FormItem>
                <FormLabel required className="text-white">
                  Password
                </FormLabel>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" />
                  <FormControl>
                    <Input
                      {...field}
                      type="password"
                      className="pl-10 bg-gray-800 border-gray-700 text-white"
                      placeholder="Create a password"
                    />
                  </FormControl>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="confirmPassword"
            rules={{
              required: "Please confirm your password",
              validate: (value) => value === form.getValues("password") || "Passwords do not match",
            }}
            render={({ field }) => (
              <FormItem>
                <FormLabel required className="text-white">
                  Confirm Password
                </FormLabel>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5" />
                  <FormControl>
                    <Input
                      {...field}
                      type="password"
                      className="pl-10 bg-gray-800 border-gray-700 text-white"
                      placeholder="Confirm your password"
                    />
                  </FormControl>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          <Button type="submit" className="w-full bg-blue-600 hover:bg-blue-700 text-white">
            Create Account
          </Button>
        </form>
      </Form>
    </motion.div>
  )
}

