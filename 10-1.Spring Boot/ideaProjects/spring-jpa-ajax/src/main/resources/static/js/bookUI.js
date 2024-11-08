import {getAllBooks, addBook, getBook, updateBook, goRemove, findBook} from './bookApi.js'


const init =()=>{
    getAllBooks(); // 모든 도서목록 가져오기
    // alert("init");
    const btnALL = document.querySelector("#btnAll");
    const btnCreate = document.querySelector("#btnCreate");
    const btnUpdate = document.querySelector("#btnUpdate");
    const btnSearch= document.querySelector("#btnSearch");
    const inputSearch= document.querySelector("#keyword");   //input

    btnALL.onclick=()=>{        // 모두 보기 버튼
        getAllBooks();
    }// btnAll end------------

    inputSearch.onkeyup=(event)=>{
        if(event.keyCode == 13){
            const keyword =  event.target.value;
            if(!keyword) return;
            findBook(keyword);
        }
    }


    // GET  /api/books?search=검색어
    btnSearch.onclick=()=>{
        // 1. 검색 keyword 값 얻어오기
        const keyword = document.querySelector("#keyword");

        // 2. 유효성 체크
        if(!keyword.value){
            alert("검색할 단어를 입력하세요")
            keyword.focus();
            return;
        }

        // 3. bookAPI.js의 findBook(키워드)
        findBook(keyword.value.trim());
    }

    btnCreate.onclick=()=>{     // 등록 버튼
        //사용자가 입력한 값 받기
        const title = document.querySelector("#title").value;
        const publish = document.querySelector("#publish").value;
        const price = document.querySelector("#price").value;

        // 유효성 체크
        if(!title || !publish || !price){
            alert('모든 값을 입력해야 해요.');
            return;
        }

        if(isNaN(price)){       // is not a number 함수. 숫자가 아니면 true를 반환.
            alert('가격은 숫자여야 해요');
            return;
        }

        const tmpBook = {   //json 객체로 만들자.
            "title":title,
            "publish" : publish,
            "price" : price
        }
        // api 요청을 보내는 함수 호출
        addBook(tmpBook);       //bookApi.js

    }// btnCreate end------------

    btnUpdate.onclick=()=>{
        const isbn = document.querySelector("#isbn").value;
        const title = document.querySelector("#title").value;
        const publish = document.querySelector("#publish").value;
        const price = document.querySelector("#price").value;

        // 유효성 체크
        if(!title || !publish || !price){
            alert('모든 값을 입력해야 해요.');
            return;
        }

        if(isNaN(price)){       // is not a number 함수. 숫자가 아니면 true를 반환.
            alert('가격은 숫자여야 해요');
            return;
        }

        const tmpBook = {   //json 객체로 만들자.
            "id" : isbn,
            "title" : title,
            publish,
            price
        }
        console.log("tmpBook==={}",tmpBook);        // 속성명과 변수명이 같으면 축약해서 사용 가능
        updateBook(tmpBook);

    }// btnUpdate end------------

}// init end -------------

// 윈도우 객체에 getBook 함수 등록
window.getBook =getBook;
window.goRemove = goRemove;
// 모듈에서 정의된 변수나 함수는 해당 모듈에서만 접근 가능하다.
// export하면 다른 모델에서 import해서 사용할 수 있다.
// 모듈화한 함수를 html@에서 사용하려면 window 객체에 속성으로 등록해줘야 한다.

document.addEventListener('DOMContentLoaded', init);