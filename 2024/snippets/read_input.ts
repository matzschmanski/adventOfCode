import * as fs from 'fs';
import * as path from 'path';



export const readTxtFile = (name: string): string => {
    const relativeFilePath = `inputs/${name}`;
    const absolutePath = path.resolve(relativeFilePath);
    let data :string ="";
    try {
        data = fs.readFileSync(absolutePath, 'utf8');
      } catch (err) {
        console.error('Error reading the file:', err);
      }
      return data;

};

export const readTxtFileLines = (name: string): string[] => {
    const content = readTxtFile(name);
    return content ? content.split(/\r?\n/) : [];
};