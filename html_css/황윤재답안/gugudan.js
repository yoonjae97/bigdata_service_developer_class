const answer = document.querySelector('.answer');

const forBtn = () => {
    answer.innerHTML += '<p>for문을 이용한 구구단</p>'
    const val = document.querySelector('#text').value
    for (i = 1; i<=9; i++) {
        answer.innerHTML += `${val} x ${i} = ${val*i}<br>`
    }
}

const whileBtn = () => {
    answer.innerHTML += '<p>while문을 이용한 구구단</p>'
    let i = 1
    const val = document.querySelector('#text').value
    while (i<=9) {
        answer.innerHTML += `${val} x ${i} = ${val*i}<br>`;
        i++;
    }
}