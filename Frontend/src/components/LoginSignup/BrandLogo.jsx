import { motion } from "framer-motion"
import { Heart } from "lucide-react"

export default function BrandLogo() {
  const textVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
      opacity: 1,
      y: 0,
      transition: {
        duration: 0.5,
        staggerChildren: 0.1,
      },
    },
  }

  const letterVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
      opacity: 1,
      y: 0,
    },
  }

  return (
    <motion.div className="flex items-center space-x-2" initial="hidden" animate="visible" variants={textVariants}>
      <motion.div
        whileHover={{ scale: 1.1 }}
        whileTap={{ scale: 0.9 }}
        className="flex items-center justify-center w-12 h-12 rounded-full bg-blue-600"
      >
        <Heart className="w-6 h-6 text-white" />
      </motion.div>
      <div className="flex items-center">
        <motion.span
          variants={letterVariants}
          className="text-4xl font-bold bg-gradient-to-r from-blue-400 to-blue-600 bg-clip-text text-transparent"
        >
          HealthCheck Pro
        </motion.span>
      </div>
    </motion.div>
  )
}

