import { useState } from "react"
import { motion } from "framer-motion"
import { Sun, Moon, Menu, X } from "lucide-react"

export default function Header({ isDarkMode, toggleDarkMode }) {
  const [isMenuOpen, setIsMenuOpen] = useState(false)

  return (
    <header className="fixed w-full z-10 bg-white bg-opacity-90 dark:bg-gray-900 dark:bg-opacity-90 shadow-md">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center">
        <motion.div initial={{ opacity: 0, x: -20 }} animate={{ opacity: 1, x: 0 }} transition={{ duration: 0.5 }}>
          <h1 className="text-2xl font-bold text-blue-600 dark:text-blue-400">HealthCheck Pro</h1>
        </motion.div>
        <nav className="hidden md:flex space-x-6">
          <NavLink href="#services">Services</NavLink>
          <NavLink href="#testimonials">Testimonials</NavLink>
          <NavLink href="#stats">Stats</NavLink>
          <NavLink href="#contact">Contact</NavLink>
        </nav>
        <div className="flex items-center space-x-4">
          <button
            onClick={toggleDarkMode}
            className="p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors"
          >
            {isDarkMode ? <Sun size={24} /> : <Moon size={24} />}
          </button>
          <button
            className="md:hidden p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            {isMenuOpen ? <X size={24} /> : <Menu size={24} />}
          </button>
        </div>
      </div>
      {isMenuOpen && (
        <motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          exit={{ opacity: 0, y: -20 }}
          className="md:hidden bg-white dark:bg-gray-900 py-4"
        >
          <nav className="flex flex-col items-center space-y-4">
            <NavLink href="#services" onClick={() => setIsMenuOpen(false)}>
              Services
            </NavLink>
            <NavLink href="#testimonials" onClick={() => setIsMenuOpen(false)}>
              Testimonials
            </NavLink>
            <NavLink href="#stats" onClick={() => setIsMenuOpen(false)}>
              Stats
            </NavLink>
            <NavLink href="#contact" onClick={() => setIsMenuOpen(false)}>
              Contact
            </NavLink>
          </nav>
        </motion.div>
      )}
    </header>
  )
}

function NavLink({ href, children, onClick }) {
  return (
    <a
      href={href}
      className="text-gray-700 dark:text-gray-300 hover:text-blue-600 dark:hover:text-blue-400 transition-colors"
      onClick={onClick}
    >
      {children}
    </a>
  )
}

