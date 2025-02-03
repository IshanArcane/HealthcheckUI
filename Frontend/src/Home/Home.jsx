import React from 'react'
import { useState, useEffect } from "react"
import { motion, useAnimation } from "framer-motion"
import Header from '../components/Header'
import HeroSection from '../components/HeroSection'
import FeatureHighlight from '../components/FeatureHighlight'
import ServicesSection from '../components/ServicesSection'
import TestimonialsSection from "../components/TestimonialsSection"
import HealthStatsSection from "../components/HealthStatsSection"
import CallToAction from "../components/CallToAction"
import Footer from '../components/Footer'
import WaveAnimation from "../components/WaveAnimation"

const Home = () => {
    const [isDarkMode, setIsDarkMode] = useState(false)
    const controls = useAnimation()

    useEffect(() => {
        controls.start({
          opacity: 1,
          y: 0,
          transition: { duration: 0.5, ease: "easeOut" },
        })
      }, [controls])    

    const toggleDarkMode = () => {
        setIsDarkMode(!isDarkMode)
      }

  return (
    <div className={`min-h-screen ${isDarkMode ? "dark" : ""}`}>
      <div className="bg-gradient-to-b from-blue-100 to-white dark:from-gray-900 dark:to-gray-800 text-gray-900 dark:text-white">
      <Header isDarkMode={isDarkMode} toggleDarkMode={toggleDarkMode} />
      <WaveAnimation />
        <motion.div initial={{ opacity: 0, y: 20 }} animate={controls}>
        <HeroSection />
        <FeatureHighlight />
        <ServicesSection />
        <TestimonialsSection />
        <HealthStatsSection />
        <CallToAction />
        <Footer />
        </motion.div>
      </div>
    </div>
  );
};

export default Home
