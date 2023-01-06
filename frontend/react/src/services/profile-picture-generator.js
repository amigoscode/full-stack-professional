export default (gender) =>
    `https://randomuser.me/api/portraits/${gender === "MALE" ? 'men' : 'women'}/${Math.floor(Math.random() * (100 - 1 + 1) + 1)}.jpg`