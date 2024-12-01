const path = require("path");
const fs = require("fs");

let jets = [];
const input = fs
  .readFileSync(path.join(__dirname, "input.txt"), "utf8")
  .toString()
  .trim()
  .split("\n")
  .flatMap((line) => {
    jets = line.split("");
  });

module.exports = {
  jets,
};