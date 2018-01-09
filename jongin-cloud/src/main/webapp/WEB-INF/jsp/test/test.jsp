<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

</head>
<body>
    <div id="app">

        <h3>{{ message }}</h3>

        <button v-on:click="clickBtn">axios</button>

    </div>

    <script>

        var app = new Vue({
          el: '#app',
          data: {
            message : 'Hello Vue.js',
          },
          methods: {
        
            // params: {
            //             memberNo : 9,
            //             id : "cv613",
            //             pass : "chvy918",
            //             lockMode : 'T'
            //         }

            clickBtn() {
                // var params2 = new URLSearchParams()
                // params2.append('memberNo', 9);
                // params2.append('id', 'cv613');
                // params2.append('pass', 'chvy918');
                // params2.append('lockMode', 'T');


                axios.post('${pageContext.request.contextPath}/login/lock.json', {
                         
                        memberNo : 9,
                        id : "cv613",
                        pass : "chvy918",
                        lockMode : 'T'
                    
                })
            //   axios({
            //       method : "get",
            //       url : "${pageContext.request.contextPath}/forum/forum.json",
            //       data : {
            //          start : 0,
			// 	        count : 20
            //       },
            //   })
              .then(function (res) {
                console.log(JSON.parse(res.data.result));
              })
              .catch(function (error) {
                console.log("error");
              });
        
            }
            
          }
        });
          
        </script>
</body>
</html>


