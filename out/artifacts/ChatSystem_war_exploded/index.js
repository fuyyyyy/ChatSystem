function pullUp() {
    var menu = document.getElementById("opMenu");
    var state = menu.style.visibility;
    menu.style.visibility = "hidden";
}
function back() {
    var sw = document.getElementById("openMenu");
    sw.style.color = "rgba(167,26,24,0.5)";
    pullUp();
}
function dropDown() {
    var menu = document.getElementById("opMenu");
    var state = menu.style.visibility;
    menu.style.visibility = "visible";
}
function keep() {
    var sw = document.getElementById("openMenu");
    sw.style.color = "brown";
    dropDown();
}
function Open() {
    var list = document.getElementById("v1");
    var target = document.getElementById("t1")
    if(list.style.display !== "block") {
        list.style.display = "block";
        target.innerHTML = "&nbsp ▼好友";
    }
    else {
        list.style.display = "none";
        target.innerHTML = "&nbsp ▶好友";
    }
}
function Open2() {
    var list = document.getElementById("v2");
    var target = document.getElementById("t2");
    if(list.style.display !== "block") {
        list.style.display = "block";
        target.innerHTML = "&nbsp ▼群聊";
    }
    else {
        list.style.display = "none";
        target.innerHTML = "&nbsp ▶群聊";
    }
}