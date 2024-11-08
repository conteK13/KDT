import {getAllBooks, addBook, getBook} from './bookApi.js'


const init =()=>{
    // alert("init");
    const btnALL = document.querySelector("#btnAll");
    const btnCreate = document.querySelector("#btnCreate");
    const btnUpdate = document.querySelector("#btnUpdate");

    btnALL.onclick=()=>{        // 모두 보기 버튼
        getAllBooks();
    }// btnAll end------------

    btnCreate.onclick=()=>{     // 등록 버튼
        //사용자가 이력한 값 받기
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

    }// btnUpdate end------------

}// init end -------------

// 윈도우 객체에 getBook 함수 등록
window.getBook =getBook;
// 모듈에서 정의된 변수나 함수는 해당 모듈에서만 접근 가능하다.
// export하면 다른 모델에서 import해서 사용할 수 있다.
// 모듈화한 함수를 html@에서 사용하려면 window 객체에 속성으로 등록해줘야 한다.

document.addEventListener('DOMContentLoaded', init);