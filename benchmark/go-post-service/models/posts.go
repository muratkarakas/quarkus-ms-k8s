package models

// Users - Model for the uses table
type Posts struct {
	Id     int    `json:"id""`
	Name   string `json:"name" orm:"size(128)"`
	UserId int    `json:"userId" orm:"auto"`
}
