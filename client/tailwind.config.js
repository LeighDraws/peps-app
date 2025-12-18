/**@type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,ts}'],
  theme: {
    extend: {
      fontFamily: {
        main: ['"Poppins"', 'sans-serif'],
        alt: ['"Josefin Sans"', 'sans-serif'],
        fancy: ['"Peanut Butter"', 'cursive'],
      },
    },
  },
  plugins: [require('@tailwindcss/typography'), require('daisyui')],
  daisyui: {
    themes: ['peps-theme'], // 🔥 thème personnalisé
  },
};
