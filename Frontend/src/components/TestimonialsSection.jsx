import { useState, useEffect } from "react"
import { motion, AnimatePresence } from "framer-motion"

const testimonials = [
  {
    name: "John Doe",
    role: "Patient",
    content: "HealthCheck Pro has revolutionized how I manage my health. It's user-friendly and comprehensive!",
  },
  {
    name: "Jane Smith",
    role: "Healthcare Provider",
    content:
      "As a doctor, I find HealthCheck Pro invaluable for managing patient records and appointments efficiently.",
  },
  {
    name: "Mike Johnson",
    role: "Health Enthusiast",
    content: "The health monitoring dashboard has helped me stay on top of my fitness goals. Highly recommended!",
  },
]

export default function TestimonialsSection() {
  const [currentIndex, setCurrentIndex] = useState(0)

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % testimonials.length)
    }, 5000)
    return () => clearInterval(timer)
  }, [])

  return (
    <section id="testimonials" className="py-20 bg-blue-50 dark:bg-gray-900">
      <div className="container mx-auto px-4">
        <h2 className="text-3xl md:text-4xl font-bold text-center mb-12">What Our Users Say</h2>
        <div className="relative h-64">
          <AnimatePresence initial={false}>
            <motion.div
              key={currentIndex}
              initial={{ opacity: 0, x: 100 }}
              animate={{ opacity: 1, x: 0 }}
              exit={{ opacity: 0, x: -100 }}
              transition={{ duration: 0.5 }}
              className="absolute inset-0 flex items-center justify-center"
            >
              <div className="bg-white dark:bg-gray-800 rounded-lg shadow-lg p-8 max-w-2xl text-center">
                <p className="text-lg mb-4 text-gray-700 dark:text-gray-300">"{testimonials[currentIndex].content}"</p>
                <p className="font-semibold">{testimonials[currentIndex].name}</p>
                <p className="text-sm text-gray-600 dark:text-gray-400">{testimonials[currentIndex].role}</p>
              </div>
            </motion.div>
          </AnimatePresence>
        </div>
      </div>
    </section>
  )
}

