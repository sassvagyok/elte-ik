const taskA = document.querySelector('#taskA')
const taskB = document.querySelector('#taskB')
const taskC = document.querySelector('#taskC')
const taskD = document.querySelector('#taskD')
const taskE = document.querySelector('#taskE')

// 1.
const che_sum = participants.filter(x => x.nation === "CHE").length;
taskA.innerHTML = che_sum

// 2.
const has_hun = participants.some(x => x.nation === "HUN");
taskB.innerHTML = has_hun ? "Igen" : "Nem";

// 3.
const has_2_spaces = participants.map(x => x.name.split(" ")).filter(x => x.length > 2)[0].join(" ");
taskC.innerHTML = has_2_spaces;

// 4.
const unique = [...new Set(participants.map(x => x.nation))].map(x => nations[x]);
taskD.innerHTML = unique.join(",");

// 5.
const participation_count = {};

for (let participant of participants) {
    if (participation_count[participant.nation]) {
        participation_count[participant.nation]++;
    } else {
        participation_count[participant.nation] = 1;
    }
}

let max_participant;
let max = Number.MIN_VALUE;

for (let participant in participation_count) {
    if (participation_count[participant] > max) {
        max = participation_count[participant];
        max_participant = participant;
    }
}

taskE.innerHTML = nations[max_participant]