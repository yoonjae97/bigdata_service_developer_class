let spanTag = document.getElementById('spanTag');

const increase = () => {
    let spanNum = Number(spanTag.innerText);
    spanTag.innerText = spanNum + 1;
}

const decrease = () => {
    let spanNum = Number(spanTag.innerText);
    if (spanNum > 0) {
    spanTag.innerText = spanNum - 1;
    }
}

const reset = () => {
    let spanNum = Number(spanTag.innerText);
    spanTag.innerText = 0;
}
