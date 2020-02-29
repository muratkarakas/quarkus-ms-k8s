package main

import (
	"net/http"

	"go-post-service/models"

	"github.com/astaxie/beego/orm"
	"github.com/gin-gonic/gin"
)

var ORM orm.Ormer

func init() {
	models.ConnectToDb()
	ORM = models.GetOrmObject()
}

func main() {
	router := gin.New()

	router.GET("/posts", readUsers)

	router.Run(":3000")
}

func readUsers(c *gin.Context) {
	var post []models.Posts
	_, err := ORM.QueryTable("posts").All(&post)
	if err == nil {
		c.JSON(http.StatusOK, &post)
	} else {
		c.JSON(http.StatusInternalServerError,
			gin.H{"status": http.StatusInternalServerError, "error": "Failed to read the users"})
	}
}
