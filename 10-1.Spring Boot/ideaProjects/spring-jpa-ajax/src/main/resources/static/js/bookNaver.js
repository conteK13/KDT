let display= 12;
start = 1;
const baseUrl=`/api/naver`;     // 백엔드 컨트롤러로 요청을 보내자.(same domain)
// `https://openapi.naver.com/v1/search/book.json`;===>
// sop 정책에 의해 서로 다른 도메인이므로 CORS를 설정하지 않아 에러 발생
// SOP : Same Origin Policy(동일 출처 정책)
// CORS : Cross Origin Resource Sharing (교차 출처 공유)
// ===> 도메인이 달라도 서버쪽에서 허용하면 리소스를 공유할 수 있다.
// java를 이용해 proxy Server(요청을 대신 보내주는 서버)를 구현하여
// 대신 데이터를 받아오고, 이걸 클라이언트 쪽으로 전달하는 방식으로 해결해보자

// '/api/books';        ===> 같은 도메인이므로 데이터 잘 받아옴

function init(){
    // alert('init');
    const btnFind = document.querySelector("#btnFind");
    const input = document.querySelector("#keyword");
    const form = document.querySelector("#searchFrm");

    // 폼을 전송하려고 할 때, 이벤트 핸들러
    // ==>ajax 처리할 때는 form이 submit하지 못하도록 막아야 한다.
    form.onsubmit=(evt)=>{
        //alert('a');
        evt.preventDefault();       // 이벤트의 기본동작 방식을 막는다.
                                    // submit 하려는 기본 동작을 막는다.

        if(!input.value){
            alert('검색할 도서명을 입력하세요');
            input.focus();
            return;
        }
        let query = input.value.trim();


        let url = `${baseUrl}?query=${query}&display=${display}&start=${start}`;
        send(url, query, start);

        // 원하지 않는 이벤트의 기본동작을 막는 방법.
        // [1] return false;
        // [2] envent evt를 매개변수로 입력받아서
        //     preventDefault()를 호출하여 이벤트의 기본 동작 방식을 막는다.
    }// onsubmit end-------------------------

}// init------------------------
// fetch(), axios()==> 라이브러리 필요함
async function send(url, query, page){
    try{
        const response = await axios.get(url);
        const data = await response.data;

        showList(data, query);           // 배열로 받은 데이터를 html로 보여주는 함수
        showPage(data, query, page)
        //alert(JSON.stringify(data))
//        const div = document.querySelector("#result");
//        let str = `<h3> 제목 : ${data.items[0].title} </h3>
//        <h3> 상세링크 : ${data.items[0].link} </h3>
//        <h3> 작가 : ${data.items[0].author} </h3>
//        <h3> 출판사 : ${data.items[0].publisher} </h3>
//        <img src="${data.items[0].image}">
//        `;
//        div.innerHTML = str;
    }catch(err){
        alert('error: ' + err)
    }
}// send()-------------------

function send_old(url, query, start){
    axios.get(url)
    .then((response)=>{
        alert(JSON.stringify(response.data))
    })
    .catch(err=>alert('error: '+err));
} // send_old()--------------

function showPage(data, query, page){
    let {total} = data;
    // 검색 결과 240개 초과하면 240개로 제한 두자
    if(total>240){
        total=240;
    }
    // total : 검색 글 수
    // display : 한 페이지당 보여줄 목록 갯수
    // java의 경우 pageCount =(total-1)/display +1;
    // javascript : pageCount=Math.ceil(total/display)

    let pageCount = Math.ceil(total/display);
    console.log('PageCount : ', pageCount, 'total', total,  'display : ', display);

    let str=`<ul class="pagination justify-content-center">`;
        for(let i=1; i<=pageCount;i++){

            //////////////////////////////
            let start = (i-1)*display+1;
            //////////////////////////////

            if (i==page){
                str+=`<li class="page-item active">`;

            }else{
                str+=`<li class = "page-item">`;
            }

            str+=`<a class="page-link" href="#"
            onclick="fetchData('${query}', ${start}, ${i})"> ${i}
            </a> </li>`;
        }
    str += `</ul>`
    document.querySelector('#pageNavi').innerHTML=str;
}


function fetchData(query, start, page){
    let url = `${baseUrl}?query=${query}&display=${display}&start=${start}`;
    console.log(url);
    send(url, query, page)
}

function showList(data,query){
    const {items, start, total} = data;
    let str = `<h2 class = "text-primary text-center"> 검색어 : ${query}, 총 ${total}권 </h2>`;
    str+= `<table class = "table"><tr>`;
    // 배열.foreach((item, i) => {})
    items.forEach((book, i) =>{
        str+=`<td style="width:25%; text-align:center">
                <a href="${book.link}" target="_blank">
                    <img src="${book.image}" class="img-thumbnail" style="width:65%">
                </a>
                <br>
                <h5 class="mt-3"> ${book.title} </h5>
                <p>
                    저자 : ${book.author} <br>
                    출판사 : ${book.publisher}<br>
                </p>
            </td>
        `;

        if(i%4 ==3){
            str +=`</tr><tr>`;
        }
    })
    str+= `</tr> </table>`;

    let div = document.querySelector("#result");
    div.innerHTML = str;
}

document.addEventListener('DOMContentLoaded', init);


