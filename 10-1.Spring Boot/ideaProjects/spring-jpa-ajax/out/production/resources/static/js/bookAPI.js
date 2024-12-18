const baseUrl ='/api/books'
// GET /api/books       ==> 모든 도서 가져오기
// GET /api/books/100   ==> 100번 도서 가져오기
//------------------------------------------
// POST     /api/books          ==> 새로운 도서 정보 등록
// DELETE   /api/books/15       ==>15번 도서 정보 삭제하기
// PUT      /api/books          ==> 도서정보 수정하기

const addBook=async(newBook)=>{
    try{
        // post 방식일때는 method에 post를 명시한다.
        // 파라미터 데이터 형태로 보내는 것이 아니라 json 유형으로 데이터를 보내려면
        // headers에 'Content-Type' : 'application/json'를 기술해야 함.
        // json 데이터는 문자열로 직렬화해서 보내야 함.
        const response = await fetch(baseUrl, {
            method:'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(newBook)
        })
        const data = await response.json();
        // alert(JSON.stringify(data))
        if(data.status == "success"){
            getAllBooks();  // 목록 가져오기
        }else{
            alert(data.message);
        }
    }catch(error){
        alert('Error: ' +error);
        console.error(error)
    }

}

const findBook=async(keyword)=>{
    try{
        let url = baseUrl+`?search=${keyword}`;
//        let url = baseUrl+"?search="+keyword;
        const response=await fetch(url);
        const data = await response.json();
        renderBooks(data);
    }catch(error){
        alert('Error: ' +error);
    }
}

const getAllBooks= async ()=>{
    try{
        const response=await fetch(baseUrl);
        const data = await response.json();
        renderBooks(data);
    }catch(error){
        alert('Error: ' +error);
    }
}

const getAllBooks_old=()=>{
    fetch(baseUrl, {
        method: 'GET'
        //cache: "no-cache"
    })
    .then((response) => {
        if (!response.ok){
            throw new Error('Error:네트워크 오류')
        }
        return response.json();
    })
    .then((data)=>{
        // alert(JSON.stringify(data));
        renderBooks(data);
    })

    .catch((error)=>{ //에러가 날 경우 catch()
        alert('error:' + error);
    })
}// getAllBooks end---------


const renderBooks=(data)=>{
    const result = document.querySelector("#result");   // ==>tbody 태그
    let str = '';
    // 배열.forEach(콜백함수)
    data.forEach((book)=>{
        str+=  `<tr>
                    <td> ${book.title} </td>
                    <td> ${book.publish}</td>
                    <td> ${book.price}</td>
                    <td> ${book.id}</td>
                    <td>
                        <button class = 'btn btn-warning'
                        onclick='getBook("${book.id}")'> 수정 </button>

                        <button class = 'btn btn-secondary'
                        onclick='goRemove("${book.id}")'> 삭제 </button>
                    </td>
                </tr>`
    })//forEach 반복문
    result.innerHTML=str;
    //getBook("${book.id}")
}

const getBook= async(id)=>{
    // alert(id);
    // 그걸 받아서 input에 value 넣어주기
    // document.querySelector('#아이디').value = "받은값"
    try{
        // GET /api/books/21
        // fetch()이용해서 요청 보내기 ===> 해당 도서 정보를 json 유형으로 백엔드에서 보낸다.
        const response = await fetch(baseUrl+"/"+id);
        // baseUrl+`/${id}`
        const data = await response.json();       // 응답이 올때까지 기다려
        setFormData(data);
    }catch(error){
        alert('Error: ' +error);
    }
}

const setFormData=(data)=>{
    if(!data){
        alert("해당 도서 정보가 없습니다");
        return
    }else{
        document.querySelector("#isbn").value = data.id;
        document.querySelector("#title").value = data.title;
        document.querySelector("#publish").value = data.publish;
        document.querySelector("#price").value = data.price;
        let str = '';

        if(data.bookImage!=null){
            str=`<img src="/images/${data.bookImage}" class="img img-thumbnail" alt ="${data.title}">`
        }else{
            // 도서 정보가 없을 경우 ==> noimage.jpg
            str=`<img src="/images/noimage.jpg" class="img img-thumbnail" alt ="${data.title}">`
        }
        document.querySelector("#bookImage").innerHTML=str;

        const btnUpdate = document.querySelector("#btnUpdate");
        btnUpdate.removeAttribute('disabled');
    }
}// getBook----------------

// PUT /api/books   : 응답body 수정할 도서 정보
const updateBook=async(tmpBook)=>{
    try{
        const response = await fetch(baseUrl,{
            method: 'PUT',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(tmpBook)
        })
        const data = await response.json();
        if(data.status =="success"){
            getAllBooks();
            // input 초기화
            clearInput();
            // 수정버튼 비활성화(disabled)
            document.querySelector("#btnUpdate").setAttribute("disabled", "disabled"); // 비활성화
        }else{
            alert(data.message);
        }
    }catch(error){
        alert("Error" + error);
    }
}//updateBook-----------

const clearInput=()=>{
    document.getElementById("isbn").value = "";
    document.getElementById("title").value = "";
    document.getElementById("publish").value = "";
    document.getElementById("price").value = "";
    document.getElementById("bookImage").innerHTML="";

    document.getElementById("title").focus();       // 입력 포커스 추가
}

const goRemove=async(id)=>{
    let yn = confirm(id+"번 도서를 삭제할까요?")
    if(!yn) return;
    // DELETE /api/books/3
    let url = baseUrl+`/${id}`;
    const response = await fetch(url, {
        method:'DELETE'
    })
    const data = await response.json();
    getAllBooks();
}//--------------------------------


export {getAllBooks, addBook, getBook, updateBook, goRemove, findBook}