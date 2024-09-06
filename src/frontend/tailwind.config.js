/** @type {import('tailwindcss').Config} */
export default {
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
        'brand-purle-50': '#E0AAFF',
        'brand-purple-100': '#C77DFF',
        'brand-purple-150': '#9D4EDD',
        'brand-purple-200': '#7B2CBF',
        'brand-purple-250': '#5A189A',
        'brand-purple-300': '#3C096C',
        'brand-purple-350': '#240046',
        'brand-purple-450': '#10002B',
        // Añade más colores personalizados aquí
      },
    },
  },
  plugins: [],
}

