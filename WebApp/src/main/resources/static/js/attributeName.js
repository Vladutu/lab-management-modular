function setAttributeName(id) {
    var dateId = "date" + id;
    var labId = "labId" + id;

    var dateElem = document.getElementById(dateId);
    var labIdElem = document.getElementById(labId);

    $(dateElem).attr("name", "date");
    $(labIdElem).attr("name", "id");
}