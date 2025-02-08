import { motion } from "framer-motion"
import { Shield, Zap, Heart } from "lucide-react"

const features = [
  {
    icon: Shield,
    title: "Secure & Private",
    description: "Your health data is protected with state-of-the-art encryption.",
  },
  {
    icon: Zap,
    title: "Fast & Efficient",
    description: "Get quick access to your health information and services.",
  },
  {
    icon: Heart,
    title: "Personalized Care",
    description: "Receive tailored health recommendations and insights.",
  },
]

export default function FeatureHighlight() {
  return (
    <section className="py-20 bg-gradient-to-b from-white to-blue-50 dark:from-gray-900 dark:to-gray-800">
      <div className="container mx-auto px-4">
        <h2 className="text-3xl md:text-4xl font-bold text-center mb-12">Why Choose HealthCheck Pro?</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <motion.div
              key={index}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: index * 0.1 }}
              className="bg-white dark:bg-gray-700 rounded-lg shadow-lg p-6 text-center"
            >
              <motion.div
                whileHover={{ scale: 1.1 }}
                className="inline-block p-3 bg-blue-100 dark:bg-blue-900 rounded-full mb-4"
              >
                <feature.icon className="text-blue-600 dark:text-blue-400 w-8 h-8" />
              </motion.div>
              <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
              <p className="text-gray-600 dark:text-gray-300">{feature.description}</p>
            </motion.div>
          ))}
        </div>
      </div>
      <BackgroundAnimation />
    </section>
  )
}

function BackgroundAnimation() {
  return (
    <div className="absolute inset-0 overflow-hidden pointer-events-none">
      <motion.svg width="100%" height="100%" viewBox="0 0 100 100" preserveAspectRatio="none" className="opacity-5">
        {[...Array(10)].map((_, i) => (
          <motion.path
            key={i}
            d="M0 100 C 20 0, 50 0, 100 100 Z"
            fill="none"
            stroke="#3B82F6"
            strokeWidth="0.5"
            initial={{ pathLength: 0, opacity: 0 }}
            animate={{
              pathLength: 1,
              opacity: 0.5,
              pathOffset: [0, 1],
            }}
            transition={{
              duration: Math.random() * 5 + 5,
              repeat: Number.POSITIVE_INFINITY,
              repeatType: "loop",
              ease: "linear",
            }}
          />
        ))}
      </motion.svg>
    </div>
  )
}

