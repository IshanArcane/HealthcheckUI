import { motion } from "framer-motion"
import { ArrowRight, Check } from "lucide-react"

export default function CallToAction() {
  const benefits = [
    "Access your health records anytime, anywhere",
    "Schedule appointments with ease",
    "Receive personalized health insights",
    "Secure communication with healthcare providers",
  ]

  return (
    <section className="py-20 bg-blue-600 dark:bg-blue-800 text-white relative overflow-hidden">
      <div className="container mx-auto px-4 relative z-10">
        <div className="flex flex-col md:flex-row items-center justify-between">
          <div className="md:w-1/2 mb-8 md:mb-0">
            <h2 className="text-3xl md:text-4xl font-bold mb-4">Ready to Take Control of Your Health?</h2>
            <p className="text-xl mb-6">
              Join thousands of satisfied users who have transformed their healthcare experience with HealthCheck Pro.
            </p>
            <ul className="space-y-2 mb-8">
              {benefits.map((benefit, index) => (
                <motion.li
                  key={index}
                  initial={{ opacity: 0, x: -20 }}
                  animate={{ opacity: 1, x: 0 }}
                  transition={{ duration: 0.5, delay: index * 0.1 }}
                  className="flex items-center"
                >
                  <Check className="mr-2 flex-shrink-0" />
                  <span>{benefit}</span>
                </motion.li>
              ))}
            </ul>
            <motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              className="bg-white text-blue-600 font-bold py-3 px-8 rounded-full inline-flex items-center transition-colors hover:bg-blue-50"
            >
              Start Your Free Trial
              <ArrowRight className="ml-2" size={20} />
            </motion.button>
          </div>
          <div className="md:w-1/2">
            <motion.img
              src="/placeholder.svg?height=400&width=400"
              alt="HealthCheck Pro App"
              className="rounded-lg shadow-2xl"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.8 }}
            />
          </div>
        </div>
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
            r={Math.random() * 1 + 0.5}
            fill="#FFFFFF"
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

