# OnTrack

Not just another task management app.

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### What is OnTrack?
OnTrack is an app for students looking to increase their daily focus and drive by making their productivity more tangible, inspired by Scrum, an Agile project management framework engineers use to address complex problems.


### App Evaluation
- **Category:** Task Management
- **Mobile:** This app uses the camera and real-time push notifications.
- **Story:** By helping students keep track of their completed and desired accomplishments, this app helps students maintain high morale, motivation, and work-life balance.
- **Market:** This app is geared toward college students who hope to be more productive, and who feel they have trouble keeping themselves motivated.
- **Habit:** This app would be used daily as a way to keep users focused and motivated in their tasks.
- **Scope:** First we would start with students tracking their own tasks. We would then add functionality to add attachments to tasks in order to track relevant documents and information. We would then add capability for users to share batons (tasks) with others in order to collobarate with them. That would then include the ability to track batons that have been passed on. In the long run, it would be great to expand the user base beyond college students.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Database keeping track of users, checkpoints (high-level tasks), and batons (lower-level breakdown of tasks)
* Push notifications for reminders to complete tasks
* Camera to add photos to checkpoints
* Create data visualisations of completed tasks

**Optional Nice-to-have Stories**

* Location-tracking
* Social connection
* Send baton as a way to collaborate with another user
* Alert of a new baton coming in
* See friend's profile
* Compare completed batons

### 2. Screen Archetypes

* Login
    * User logs in to existing account
    * App remembers previous log in and persists the user until log out
* Register
    * User signs up for an account
* Add checkpoints to backlog
   * Keep track of high level tasks that need to be done
* Add batons to checkpoint
   * Break down large task to smaller, more feasible batons and add a weight to each of them
* Attach files to checkpoint
    * Using camera or phone file system, attach relevant files to checkpoint
* Initialize lap
    * Set lap length and preferences
* This lap
   * See incomplete checkpoints for lap in progress
* Lap time
    * See relevant stats for lap in progress
* Highlights
    * See visualisations of how well the user did in this lap
    * Allow user to do a retro where they reflect on:
        * Successful actions in this lap
        * Areas in which they can improve
        * Hurdles they ran into

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Checkpoint Backlog
    * all high level tasks to be completed
* This Lap
    * all low-level tasks user chose to complete this lap
* Lap Time
    * relevant stats for lap in progress

**Flow Navigation** (Screen to Screen)

* Checkpoint Backlog
    * Add checkpoints to backlog
    * Add batons to checkpoint
    * Attach files to checkpoint
* Batons
    * Add attachments
    * Send baton to another user to collaborate
* Lap Time
    * This lap
    * Complete lap
* Highlights
    * Do a retro
    * Start again


## Wireframes
<img src="designs/Hand-drawn-sketches/IMG_4899.jpg" width=600>

<img src="designs/Hand-drawn-sketches/IMG_4900.jpg" width=600>

<img src="designs/Hand-drawn-sketches/IMG_4901.jpg" width=600>

### Original App Storyboard

<img src="designs/Hand-drawn-sketches/IMG_4902.jpg" width=00>

<img src="designs/Hand-drawn-sketches/IMG_4903.jpg" width=600>

<img src="designs/Hand-drawn-sketches/IMG_4904.jpg" width=600>


### Digital Wireframes & Mockups
<img src="designs/Updated-Frames/Backlog.png" width=300>
<img src="designs/Updated-Frames/Add Batons to Checkpoint/ Dialog box.png" width=300>
<img src="designs/Updated-Frames/SetLap.png" width=300>
<img src="designs/Updated-Frames/Add Batons to Checkpoint.png" width=300>
<img src="designs/Updated-Frames/Backlog Add Checkpoint.png" width=300>
<img src="designs/Updated-Frames/This Lap.png" width=300>
<img src="designs/Updated-Frames/This Lap with dialog box.png" width=300>
<img src="designs/Updated-Frames/Camera.png" width=300>
<img src="designs/Updated-Frames/Lap Time.png" width=300>
<img src="designs/Updated-Frames/Lap Complete.png" width=300>
<img src="designs/Updated-Frames/Replay.png" width=300>
<img src="designs/Updated-Frames/Medals.png" width=300>
<img src="designs/Updated-Frames/Coaches Comments.png" width=300>
<img src="designs/Updated-Frames/Reflection.png" width=300>




## Schema 
<img src="https://github.com/android-on-track/on-track/blob/master/designs/DatabaseSchema.PNG" width=600>

## Models
#USER
| Property Name | Type | Description  |
| :---         |     :---:      |          ---: |
| ObjectID PK  | String   | Main ID of user to be used Throughout the App    |
| username     | String       | Username      |
| password     | String       | password      |
| Email     | String       | The user Email      |

## Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
