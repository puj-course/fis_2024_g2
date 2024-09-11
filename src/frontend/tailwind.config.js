/** @type {import('tailwindcss').Config} */
export default {
  darkMode: 'class',
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        'login': "url('./src/assets/img.jpg')",
        'concert': "url('./src/assets/concert.jpg')"
      },
      colors: {
        "darkCustom": "#131416",
        "darkNavBar": "#1D1E22",
      },
    },
  },
  plugins: [],
}

