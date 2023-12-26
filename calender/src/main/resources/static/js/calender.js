document.addEventListener("DOMContentLoaded", function() {
    var currentDate = new Date();
    var currentMonth = currentDate.getMonth();
    var currentYear = currentDate.getFullYear();
    displayCalendar(currentMonth, currentYear);
});

function displayCalendar(month, year) {
    var calendarHtml = "";
    // var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    // var weekdays = ["일", "월", "화", "수", "목", "금", "토"];
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var weekdays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    calendarHtml += "<div id='calendar-title'>";
    calendarHtml +=     "<div id='calendar-before'>< </div>";
    calendarHtml +=     "<h2>" + months[month] + " " + year + "</h2>";
    calendarHtml +=     "<div id='calendar-after'>></div>";
    calendarHtml += "</div>";

    calendarHtml += "<table>";
    calendarHtml += "<tr>";
    for (var i = 0; i < weekdays.length; i++) {
        calendarHtml += "<th>" + weekdays[i] + "</th>";
    }
    calendarHtml += "</tr>";

    var firstDay = new Date(year, month, 1);
    var startingDay = firstDay.getDay();

    var lastDayOfPrevMonth = new Date(year, month, 0).getDate();

    var lastDay = new Date(year, month + 1, 0);
    var endingDay = lastDay.getDate();

    var dayCounter = 1;
    for (var i = 0; i < 6; i++) {
        calendarHtml += "<tr>";
        for (var j = 0; j < 7; j++) {
            if (i === 0 && j < startingDay) {
                calendarHtml += "<td class='other-month'>" + (lastDayOfPrevMonth - startingDay + j + 1) + "</td>";
            } else if (dayCounter > endingDay) {
                calendarHtml += "<td class='other-month'>" + (dayCounter - endingDay) + "</td>";
                dayCounter++;
            } else {
                calendarHtml += "<td";
                if (j === 0) {
                    calendarHtml += " class='sunday'";
                } else if (j === 6) {
                    calendarHtml += " class='saturday'";
                }
                calendarHtml += ">" + dayCounter + "</td>";
                dayCounter++;
            }
        }
        calendarHtml += "</tr>";
    }

    calendarHtml += "</table>";
    document.getElementById("calendar").innerHTML = calendarHtml;
}

$(document).ready(function () {
    function convertTime() {
        var now = new Date();
        var month = now.getMonth() + 1;
        var date = now.getDate();

        return month + '월' + date + '일';
    }
    var currentTime = convertTime();
    $('.nowtime').append(currentTime);
});