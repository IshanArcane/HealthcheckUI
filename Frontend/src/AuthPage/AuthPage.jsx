"use client"

import { useState } from "react"
import { motion, AnimatePresence } from "framer-motion"
import BrandLogo from "../components/LoginSignup/BrandLogo"
import LoginForm from "../components/LoginSignup/Login"
import SignupForm from "../components/LoginSignup/Signup"

export default function AuthPage() {
  const [isLogin, setIsLogin] = useState(true)

  return (
    <div className="min-h-screen bg-[#0A1929] flex">
      {/* Left Side - Brand and Text */}
      <div className="w-1/2 p-12 flex flex-col justify-center items-start">
        <BrandLogo />
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
          className="mt-8 max-w-xl"
        >
          <h1 className="text-5xl font-bold text-white mb-6">Your Health, Our Priority</h1>
          <p className="text-xl text-gray-300">
            Experience seamless healthcare management with HealthCheck Pro. Manage everything at your fingertips!
          </p>
        </motion.div>
      </div>

      {/* Right Side - Auth Forms */}
      <div className="w-1/2 p-12 flex flex-col justify-center items-center relative">
        <div className="absolute top-8 right-8 space-x-4">
          <button
            onClick={() => setIsLogin(true)}
            className={`px-6 py-2 rounded-full transition-all ${
              isLogin ? "bg-blue-600 text-white" : "bg-transparent text-gray-300 hover:text-white"
            }`}
          >
            Login
          </button>
          <button
            onClick={() => setIsLogin(false)}
            className={`px-6 py-2 rounded-full transition-all ${
              !isLogin ? "bg-blue-600 text-white" : "bg-transparent text-gray-300 hover:text-white"
            }`}
          >
            Register
          </button>
        </div>

        <AnimatePresence mode="wait">
          <motion.div
            key={isLogin ? "login" : "register"}
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            exit={{ opacity: 0, x: -20 }}
            transition={{ duration: 0.3 }}
            className="w-full flex justify-center"
          >
            {isLogin ? <LoginForm /> : <SignupForm />}
          </motion.div>
        </AnimatePresence>
      </div>
    </div>
  )
}

