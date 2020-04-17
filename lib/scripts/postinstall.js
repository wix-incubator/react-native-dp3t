const shell = require('shelljs');

const folderName = 'DP3TSDK';
const path = './lib/ios';
shell.cd(path);
shell.rm('-rf', `./${folderName}`);
cloneRepo();
removeUnrelatedFiles();

async function cloneRepo() {
  await shell.exec(`git clone https://github.com/DP-3T/dp3t-sdk-ios.git ${folderName}`);
}

async function removeUnrelatedFiles() {
  shell.cd(`./${folderName}`);
  shell.exec(`rm *`);
  shell.cp('-R', './Sources/DP3TSDK', '.');
  shell.rm('-rf', `./.github`);
  shell.rm('-rf', `./SampleApp`);
  shell.rm('-rf', `./Sources`);
  shell.rm('-rf', `./Tests`);
  shell.rm('.gitignore');
  shell.mv(`${folderName}/*`, '.');
  shell.rm('-rf', `./${folderName}`)
}

