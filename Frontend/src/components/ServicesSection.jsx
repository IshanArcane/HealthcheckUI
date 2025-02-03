import { motion } from "framer-motion"
import { Calendar, FileText, BarChart } from "lucide-react"

const services = [
  {
    icon: Calendar,
    title: "Doctor Appointments",
    description: "Book and manage your medical appointments with ease.",
  },
  {
    icon: FileText,
    title: "Medical Records Management",
    description: "Securely store and access your medical records anytime, anywhere.",
  },
  {
    icon: BarChart,
    title: "Health Monitoring Dashboard",
    description: "Track your health metrics and get personalized insights.",
  },
]

export default function ServicesSection() {
  return (
    <section id="services" className="py-20 bg-gray-100 dark:bg-gray-800">
      <div className="container mx-auto px-4">
        <h2 className="text-3xl md:text-4xl font-bold text-center mb-12">Our Services</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {services.map((service, index) => (
            <motion.div
              key={index}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: index * 0.1 }}
              whileHover={{ scale: 1.05 }}
              className="bg-white dark:bg-gray-700 rounded-lg shadow-lg p-6 transition-all duration-300 ease-in-out transform hover:-translate-y-2"
            >
              <service.icon className="text-blue-600 dark:text-blue-400 w-12 h-12 mb-4" />
              <h3 className="text-xl font-semibold mb-2">{service.title}</h3>
              <p className="text-gray-600 dark:text-gray-300">{service.description}</p>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  )
}

