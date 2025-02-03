import { useState, useEffect } from "react"
import { motion } from "framer-motion"
import { Users, Calendar, Lightbulb } from "lucide-react"

const stats = [
  { icon: Users, label: "Active Users", value: 10000 },
  { icon: Calendar, label: "Appointments Today", value: 500 },
  { icon: Lightbulb, label: "Health Insights", value: 5000 },
]

export default function HealthStatsSection() {
  const [counters, setCounters] = useState(stats.map(() => 0))

  useEffect(() => {
    const interval = setInterval(() => {
      setCounters((prevCounters) =>
        prevCounters.map((counter, index) => {
          const target = stats[index].value
          return Math.min(counter + Math.ceil((target - counter) / 20), target)
        }),
      )
    }, 50)

    return () => clearInterval(interval)
  }, [])

  return (
    <section id="stats" className="py-20 bg-white dark:bg-gray-900">
      <div className="container mx-auto px-4">
        <h2 className="text-3xl md:text-4xl font-bold text-center mb-12">Live Health Stats</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {stats.map((stat, index) => (
            <motion.div
              key={index}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: index * 0.1 }}
              className="bg-blue-50 dark:bg-gray-800 rounded-lg shadow-lg p-6 text-center"
            >
              <stat.icon className="text-blue-600 dark:text-blue-400 w-12 h-12 mx-auto mb-4" />
              <h3 className="text-xl font-semibold mb-2">{stat.label}</h3>
              <motion.p
                className="text-3xl font-bold text-blue-600 dark:text-blue-400"
                initial={{ scale: 1 }}
                animate={{ scale: [1, 1.1, 1] }}
                transition={{ duration: 0.5, repeat: Number.POSITIVE_INFINITY, repeatDelay: 2 }}
              >
                {counters[index].toLocaleString()}
              </motion.p>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  )
}

