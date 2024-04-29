const daysOfWeek = ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"];
const months = [
  "января",
  "февраля",
  "марта",
  "апреля",
  "мая",
  "июня",
  "июля",
  "августа",
  "сентября",
  "октября",
  "ноября",
  "декабря"
];

const getCurrentDate = () => {
    const currentDate = new Date()

    const dayOfWeek = daysOfWeek[currentDate.getDay()]
    const dayOfWonth = currentDate.getDate()
    const month = months[currentDate.getMonth()]

    return dayOfWeek + ' ' + dayOfWonth + ' ' + month
}

export default getCurrentDate