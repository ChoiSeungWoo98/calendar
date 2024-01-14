
let currentDate;
let currentMonth;
let currentYear;
let diaryMonths = [];
let holidays = [];
let holidayTitles = [];
// var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
var korWeekdays = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var weekdays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

document.addEventListener("DOMContentLoaded", function() {
    currentDate = new Date();
    currentMonth = currentDate.getMonth();
    currentYear = currentDate.getFullYear();
    getMonthDiaryList();
});

function getMonthDiaryList() {
    let url = '/calender/diary/find/month';
    let month = currentMonth <= 8 && currentMonth >= 0 ? '0' + (currentMonth + 1) : currentMonth + 1;
    let date = currentYear + '-' + month;
    let data = { diaryDate: date };

    CHOI.get(url, data)
        .then(function (response) {
            if(response.result === "0000") {
                let list = response.value;
                list.forEach(data => {
                    let day = data.diaryDate.split('-')[2];
                    diaryMonths.push(day);
                });

                getHoliday(date, month);
            }
        }).catch(function (error) {
        console.error(error);
    });
}

function getHoliday(eventDate, month) {
    let url = '/calender/event/find/month';
    let data = { eventDate: eventDate, month: month }
    CHOI.get(url, data)
        .then(function (response) {
            if(response.result === "0000") {
                let list = response.value;
                list.forEach(data => {
                    holidays.push(new Date(data.eventDate).getDate());
                    holidayTitles.push(data.title);
                });
            }
            displayCalendar(currentMonth, currentYear);
        }).catch(function (err) {
        console.error(err);
    })
}

function displayCalendar(month, year) {
    var calendarHtml = "";
    calendarHtml += "<div id='calendar-title'>";
    calendarHtml +=     "<div id='calendar-before'>< </div>";
    calendarHtml +=     "<div id='calendar-year-month'>" + months[month] + " " + year + "</div>";
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
        let tempCalendarHtml = "<tr>";
        for (var j = 0; j < 7; j++) {
            if (i === 0 && j < startingDay) {
                tempCalendarHtml += "<td class='other-month'>" + (lastDayOfPrevMonth - startingDay + j + 1) + "</td>";
            } else if (dayCounter > endingDay) {
                tempCalendarHtml += "<td class='other-month'>" + (dayCounter - endingDay) + "</td>";
                dayCounter++;
            } else {
                let now = false;
                let writeDiary = false;
                let holiday = false;
                if (dayCounter === currentDate.getDate() && month === currentDate.getMonth() && year === currentDate.getFullYear()) {
                    now = true;
                }
                if(diaryMonths.length != 0) {
                    let checkDay = diaryMonths[0];
                    if(dayCounter == checkDay) {
                        writeDiary = true;
                        diaryMonths.shift();
                    }
                }
                if(holidays.length != 0) {
                    let checkDay = holidays[0];
                    if(dayCounter == checkDay) {
                        holiday = true;
                        holidays.shift();
                    }
                }
                tempCalendarHtml += "<td";
                if (j === 0 || holiday) {
                    tempCalendarHtml += " class='sunday";
                    tempCalendarHtml += now ? " today" : "";
                    tempCalendarHtml += "'";
                } else if (j === 6) {
                    tempCalendarHtml += " class='saturday";
                    tempCalendarHtml += now ? " today" : "";
                    tempCalendarHtml += "'";
                } else {
                    now ? tempCalendarHtml += " class='today'" : '';
                }
                tempCalendarHtml += " style='";
                if(writeDiary) {
                    tempCalendarHtml += "text-decoration: overline; text-decoration-color: var(--calender-sun-color);";
                }
                tempCalendarHtml += " cursor: pointer;'";

                if(holiday) {
                    tempCalendarHtml += "title='" + holidayTitles[0] + "'";
                    holidayTitles.shift();
                }

                tempCalendarHtml += " onclick='goDetail(" + year + ", " + Number(month + 1) + ", " + dayCounter + ", \`" + korWeekdays[j] + "\`);'";
                tempCalendarHtml += ">" + dayCounter + "</td>";
                dayCounter++;
            }
        }
        tempCalendarHtml += "</tr>";

        if(endingDay + 7 < dayCounter) {
            continue;
        }
        calendarHtml += tempCalendarHtml;
    }

    calendarHtml += "</table>";
    document.getElementById("calendar").innerHTML = calendarHtml;

    var calendarBefore = document.getElementById('calendar-before');
    var calendarAfter = document.getElementById('calendar-after');
    var calendarYearMonth = document.getElementById('calendar-year-month');
    // calendarBefore.removeEventListener('click', handleCalendarBeforeClick);
    // calendarAfter.removeEventListener('click', handleCalendarAfterClick);

    calendarBefore.addEventListener('click', handleCalendarBeforeClick);
    calendarAfter.addEventListener('click', handleCalendarAfterClick);
    calendarYearMonth.addEventListener('click', handleCalendarYearMonthClick);
}

function goDetail(year, month, day, weekDay) {
    location.href = '/detail?year=' + year + "&month=" + month + "&day=" + day + "&weekDay=" + weekDay;
}

function handleCalendarBeforeClick() {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    getMonthDiaryList();
}

function handleCalendarAfterClick() {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    getMonthDiaryList();
}

function handleCalendarYearMonthClick() {
    var inputYear = prompt("이동할 연도를 입력해주세요(2023):");
    if(inputYear == undefined) return;
    var inputMonth = prompt("이동할 연도의 월을 입력하세요.(1-12):");
    if(inputMonth == undefined) return;

    if (inputYear && !isNaN(inputYear) && inputMonth && !isNaN(inputMonth) && inputMonth >= 1 && inputMonth <= 12) {
        currentYear = parseInt(inputYear);
        currentMonth = parseInt(inputMonth) - 1;
        getMonthDiaryList();
    } else {
        alert("올바른 값을 입력해주세요.");
    }
}

$(document).ready(function () {
    function convertTime() {
        var now = new Date();
        var month = now.getMonth() + 1;
        var date = now.getDate();

        return month + '월 ' + date + '일';
    }
    var currentTime = convertTime();
    $('.date').append(currentTime);
});