import React, { Component } from 'react';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';

import './Competences.css';

class Competences extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            coursesList: [],
            checkedList: [],
            error: null,
            generalCompenetencies: null,
            specificCompetencies: null,
            specList: null, 
            genList: null,
        };
    }

    componentDidMount() {
        this.getCourses();
    }

    getCourses = () => {
        const headers = {
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`
        };
        axios.get('http://localhost:8080/sql_crud/courses', {'headers': headers})
            .then(res => {
                const list = res.data;
                const comp = [];
                list.forEach(course => {
                    const parsedCourse = {
                        'id': course[2],
                        'name': course[4]
                    }

                    comp.push(parsedCourse);
                });

                this.setState({coursesList: comp})
            })
            .catch(err => {
                console.log(err);
            })
    }

    onCheck = (courseId, value) => {
        const { checkedList } = this.state;
        const newList = [...checkedList];

        if(value) {
            if(newList.length === 5) {
                this.setState({error: 'You cannot choose more than 5 courses.'})
            } else {
                newList.push(courseId);
                this.setState({
                    checkedList: newList, 
                    error: null,
                    generalCompenetencies: null,
                    specificCompetencies: null,
                    specList: null, 
                    genList: null,
                });
            }
        } else {
            const ind = newList.indexOf(courseId);
            newList.splice(ind, 1);
            this.setState({
                checkedList: newList, 
                error: null,
                generalCompenetencies: null,
                specificCompetencies: null,
                specList: null, 
                genList: null,
            });
        }
    }

    compare = () => {
        const { checkedList } = this.state;
        
        if(checkedList.length < 2) {
            this.setState({error: 'Please select at least 2 courses to compare'})
        } else {
            const request = {
                "numSubjects" : checkedList.length,
                "subjects": checkedList
            };
    
            const headers = {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem("jwt")}`
            };
    
            axios.put(`http://localhost:8080/compare/compare_subj`, request, {'headers': headers})
            .then(res => {
                const compList = res.data
                const specificsList = compList.subjectSpecificCompetency 
                const specifictComps = new Set();
                for(let subject in specificsList) {
                    specificsList[subject].forEach(comp => specifictComps.add(comp));
                }

                const generalList = compList.subjectGeneralCompetency 
                const generalComps = new Set();
                for(let subject in generalList) {
                    generalList[subject].forEach(comp => generalComps.add(comp));
                }

                this.setState({
                    generalCompenetencies: generalComps, 
                    specificCompetencies: specifictComps,
                    specList: specificsList,
                    genList: generalList
                })
            })
            .catch(err => {
                console.log(err)
            })
        }
    }

    renderGeneralComps = () => {
        const { coursesList, checkedList, generalCompenetencies, genList } = this.state;
        const compList = [];
        generalCompenetencies.forEach(comp => {
            compList.push(comp);
        });

        return(
            <div className="comp-table">
                <h4 className="table-title">General competencies</h4>
                {compList.map(comp => {
                    return(
                        <div className="comp-row">
                            <div className="comp-name">{ comp }</div>
                            {checkedList.map(subj => {
                                const compArr = genList[subj]
                                const ind = compArr.indexOf(comp)
                                return ind > -1 ? (
                                    <div className="comp-item">✓</div>
                                ) : <div className="comp-item"></div>
                            })}
                        </div>
                    )
                })}
                <div className="comp-row comp-sub-list">
                    <div className="empty-col"></div>
                    {checkedList.map(subj =>{
                        return (
                            <div className="comp-item comp-sub">
                                {subj}
                            </div>
                        )
                    })}
                </div>
            </div>
        )
    }

    rendeSpecificComps = () => {
        const { coursesList, checkedList, specificCompetencies, specList } = this.state;
        const compList = [];
        specificCompetencies.forEach(comp => {
            compList.push(comp);
        });

        return(
            <div className="comp-table">
                <h4 className="table-title">Specific competencies</h4>
                {compList.map(comp => {
                    return(
                        <div className="comp-row">
                            <div className="comp-name">{ comp }</div>
                            {checkedList.map(subj => {
                                const compArr = specList[subj]
                                const ind = compArr.indexOf(comp)
                                return ind > -1 ? (
                                    <div className="comp-item">✓</div>
                                ) : <div className="comp-item"></div>
                            })}
                        </div>
                    )
                })}
                <div className="comp-row comp-sub-list">
                    <div className="empty-col"></div>
                    {checkedList.map(subj =>{
                        return (
                            <div className="comp-item comp-sub">
                                {subj}
                            </div>
                        )
                    })}
                </div>
            </div>
        )
    }
      
    render() {
        const { error, coursesList, checkedList, generalCompenetencies, specificCompetencies } = this.state;
       
        return (
            <div className="Competences container">
                <h1 className="comp-title">Competence viewer</h1>
                <h3 className="comp-subtitle">Please select 2 to 5 courses to view competences:</h3>
                <div className="courses">
                    <Form>
                        {coursesList.map(course => (
                            <Form.Check 
                                key={course.id}
                                type="checkbox"
                                id={course.id}
                                label={course.name}
                                checked={checkedList.indexOf(course.name) !== -1}
                                onChange={event => this.onCheck(course.name, event.target.checked)}
                            />
                        ))}
                    </Form>
                    <div className="comp-error">{error}</div>
                    <Button variant="primary" className="comp-btn" onClick={this.compare}>
                        Compare
                    </Button>
                </div>
                <div className="comp-compared">
                    {generalCompenetencies ? this.renderGeneralComps() : null}
                    {specificCompetencies ? this.rendeSpecificComps() : null}
                </div>
            </div>
        );
    }
}

export default Competences;