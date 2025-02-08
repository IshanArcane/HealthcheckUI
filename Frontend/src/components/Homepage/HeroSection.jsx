import { motion } from "framer-motion"
import { ArrowRight } from "lucide-react"

export default function HeroSection() {
  return (
    <section className="relative pt-32 pb-20 md:pt-40 md:pb-32 overflow-hidden">
      <div className="container mx-auto px-4 relative z-10">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8, delay: 0.2 }}
          className="text-center"
        >
          <h1 className="text-4xl md:text-6xl font-bold mb-6 leading-tight">
            Your Health, Our Priority
            <br />
            <span className="text-blue-600 dark:text-blue-400">Manage Everything at Your Fingertips!</span>
          </h1>
          <p className="text-xl md:text-2xl mb-8 text-gray-700 dark:text-gray-300">
            Experience seamless healthcare management with HealthCheck Pro.
          </p>
          <motion.button
            whileHover={{ scale: 1.05 }}
            whileTap={{ scale: 0.95 }}
            className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-8 rounded-full inline-flex items-center transition-colors"
          >
            Get Started
            <ArrowRight className="ml-2" size={20} />
          </motion.button>
        </motion.div>
      </div>
      <BackgroundAnimation />
    </section>
  )
}

function BackgroundAnimation() {
  return (
    <div className="absolute inset-0 overflow-hidden pointer-events-none">
      <motion.svg width="100%" height="100%" viewBox="0 0 100 100" preserveAspectRatio="none" className="opacity-10">
        {[...Array(20)].map((_, i) => (
          <motion.circle
            key={i}
            cx={Math.random() * 100}
            cy={Math.random() * 100}
            r={Math.random() * 2 + 0.5}
            fill="#3B82F6"
            initial={{ opacity: 0.1, scale: 0 }}
            animate={{
              opacity: [0.1, 0.5, 0.1],
              scale: [0, 1, 0],
              cx: `${Math.random() * 100}%`,
              cy: `${Math.random() * 100}%`,
            }}
            transition={{
              duration: Math.random() * 5 + 5,
              repeat: Number.POSITIVE_INFINITY,
              repeatType: "loop",
              ease: "easeInOut",
            }}
          />
        ))}
      </motion.svg>
    </div>
  )
}

